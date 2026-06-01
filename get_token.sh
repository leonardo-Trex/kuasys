curl -s -X POST "http://localhost:8080/realms/realm-kuasys/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=leo" \
  -d "password=123" \
  -d "grant_type=password" \
  -d "client_id=backend-api" \
  -d "client_secret=Ma7ZNjU5uc9nqLolt6vIaoSEEfTHqDAK" | jq -r .access_token