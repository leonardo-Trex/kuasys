# Configuração Keycloak e Segurança com Roles

## Alterações Realizadas

### 1. **Anotações de Segurança em Resource Classes**

Todas as classes de resource foram corrigidas com as seguintes melhorias:

#### Imports Corrigidos
```java
// ✅ Correto - Usar Quarkus Security
import io.quarkus.security.Authenticated;
import io.quarkus.security.RolesAllowed;

// ❌ Evitar - Jakarta padrão não funciona bem com Keycloak
import jakarta.annotation.security.RolesAllowed;
```

#### Anotação na Classe
```java
@Path("/recurso")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated  // ← IMPORTANTE: Garante autenticação em toda resource
public class RecursoResource {
    // ...
}
```

#### PathParam nos Endpoints
```java
// ✅ Correto
@GET
@Path("/{id}")
public Response buscarPeloId(@PathParam("id") Long id) {
    // ...
}

// ❌ Incorreto - Sem PathParam
@GET
@Path("/{id}")
public Response buscarPeloId(Long id) {
    // ...
}
```

### 2. **Roles do Keycloak**

Os nomes das roles foram padronizados em **minúsculas**:
- `@RolesAllowed("admin")` - Para operações administrativas (CREATE, UPDATE, DELETE)
- GETs geralmente são acessíveis por qualquer usuário autenticado

**Exemplo:**
```java
@DELETE
@Path("/{id}")
@RolesAllowed("admin")  // ← Apenas usuários com role 'admin'
public Response deletar(@PathParam("id") Long id) {
    service.delete(id);
    return Response.noContent().build();
}
```

### 3. **Recursos Modificados**

| Resource | Mudanças |
|----------|----------|
| QuadrinhoResource | + `@Authenticated` na classe, + `@PathParam("id")`, roles em minúsculas |
| EdicaoResource | + `@PathParam("id")`, imports corrigidos, roles em minúsculas |
| ColecaoResource | + `@PathParam("id")`, imports corrigidos, roles em minúsculas |
| EditoraResource | + `@PathParam("id")`, imports corrigidos, roles em minúsculas |
| PessoaResource | + `@PathParam("id")`, imports corrigidos, roles em minúsculas |
| UsuarioResource | imports corrigidos, roles em minúsculas |

### 4. **Configuração application.properties**

```properties
# OIDC / Keycloak Configuration
quarkus.oidc.key.auth-server-url=http://localhost:8080/realms/realm-kuasys
quarkus.oidc.key.client-id=backend-api
quarkus.oidc.key.application-type=service
quarkus.oidc.key.credentials.secret=Ma7ZNjU5uc9nqLolt6vIaoSEEfTHqDAK

# Keycloak Authorization
quarkus.keycloak.policy-enforcer.enable=true
quarkus.oidc.token.audience=backend-api

# Role Mapping - Extrai roles do token JWT
quarkus.oidc.roles.role-claim-path=realm_access.roles
quarkus.oidc.roles.role-claim-separator=,
```

## Fluxo de Autenticação

```
1. Cliente envia requisição com Bearer Token (JWT do Keycloak)
2. Quarkus valida o token com Keycloak
3. Token contém as roles do usuário em "realm_access.roles"
4. @Authenticated verifica se usuário está autenticado
5. @RolesAllowed("admin") verifica se tem a role requerida
6. Requisição é processada ou rejeitada com 403 Forbidden
```

## Como Adicionar Novas Roles no Keycloak

1. Acesse o Keycloak Admin Console (http://localhost:8080/)
2. Vá para Realm → realm-kuasys → Roles
3. Crie a role (ex: "editor", "viewer")
4. Atribua a usuários através de "Client Roles" ou "Realm Roles"
5. Use nos endpoints: `@RolesAllowed("editor")`

## Testando com cURL

```bash
# Gerar token de um usuário com role 'admin'
TOKEN=$(curl -X POST http://localhost:8080/realms/realm-kuasys/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=backend-api" \
  -d "client_secret=Ma7ZNjU5uc9nqLolt6vIaoSEEfTHqDAK" \
  -d "username=admin_user" \
  -d "password=password" \
  -d "grant_type=password" | jq -r '.access_token')

# Usar token para acessar endpoint protegido
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/api/quadrinhos/1
```

## Possíveis Erros

| Erro | Causa | Solução |
|------|-------|---------|
| 401 Unauthorized | Token inválido ou expirado | Gerar novo token |
| 403 Forbidden | Usuário não tem a role requerida | Adicionar role ao usuário no Keycloak |
| "Invalid token" | Token não foi validado | Verificar `auth-server-url` e credentials |
| Role não encontrada | Importa foi de `jakarta.annotation.security` | Usar `io.quarkus.security.RolesAllowed` |

## Próximos Passos

1. **Testar endpoints** com tokens válidos
2. **Configurar o Keycloak** com usuários e roles corretos
3. **Adicionar SecurityContext** se precisar de informações do usuário autenticado:

```java
@Inject
SecurityIdentity identity;

// Usar em métodos
@GET
@Path("/me")
public Response getCurrentUser() {
    String username = identity.getPrincipal().getName();
    Set<String> roles = identity.getRoles();
    return Response.ok(new UserInfo(username, roles)).build();
}
```
