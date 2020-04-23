# demo-rest
An app example to implements REST services using Springframwork resources.

# Docker configuration
## Network
docker network create demo-rest-network
## Postgres
docker run --name demo-db --network=demo-rest-network -e "POSTGRES_PASSWORD=1234" -p 5432:5432 -d postgres:latest
## PgAdmin4
docker run --name pgadmin4 --network=demo-rest-network -p 80:80 -e "PGADMIN_DEFAULT_EMAIL=seu.email@provedor.com" -e "PGADMIN_DEFAULT_PASSWORD=1234" -d dpage/pgadmin4
