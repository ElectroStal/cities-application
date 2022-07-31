# shellcheck disable=SC2164
cd ./citiesshower
mvn -N wrapper:wrapper
# shellcheck disable=SC2103
cd ..
docker-compose up --build
