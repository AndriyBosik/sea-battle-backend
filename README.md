# Getting Started

## Docker

To run this project in docker you need to do 2 steps:

1) Build docker image:

`docker build -t sea_battle .`

3) Run container(you can also specify path to you env-file):

`docker run -d -p 8080:8080 --name sea_battle_backend sea_battle`