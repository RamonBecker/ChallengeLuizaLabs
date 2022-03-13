# ChallengeLuizaLabs
 
## :information_source: Information 

Challenge for Luizalabs

The challenge is to convert a denormalized order file to json format.

For data entry, the user must enter the number of files he wants to read. After informing the number of files, you must enter the name and extension of the file that will be read.

Data entry example:
```
test.txt

file_name.extension
```
After informing the files, it will be verified if the files exist. If they exist, they will be processed and a new json file will be created.

Example output from json file:

```

[
  {
    "user_id": 1,
    "name": "Sammie Baumbach",
    "orders": [
      {
        "date": "2021-10-28",
        "total": 2966.46,
        "order_id": 2,
        "products": [
          {
            "product_id": 2,
            "value": 798.03
          },
          {
            "product_id": 2,
            "value": 601.43
          },
          {
            "product_id": 5,
            "value": 1567
          }
        ]
      }
]
```
If you want a better view of the created json file, access the following site:
```
https://jsonformatter.org/
```
You can copy and paste every line from the json file or you can import directly from the website.


## :zap: Technologies	

- Java
- JSON
- JUNIT

## :memo: Developed features

- [x] Reading files
- [x] File creation
- [x] Writing to files

## :technologist:	 Author

By Ramon Becker 👋🏽 Get in touch!

[<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/github.svg' alt='github' height='40'>](https://github.com/RamonBecker)  [<img src='https://cdn.jsdelivr.net/npm/simple-icons@3.0.1/icons/linkedin.svg' alt='linkedin' height='40'>](https://www.linkedin.com/in/https://www.linkedin.com/in/ramon-becker-da-silva-96b81b141//)
![Gmail Badge](https://img.shields.io/badge/-ramonbecker68@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:ramonbecker68@gmail.com)
