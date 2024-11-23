# PET RESGATE IRECE
## **TO-DO**:
- Implementar tartamento de Exceção.
- Implementar getAllAnimals.
- Modificar os retornos dos metodos do Service e Controller de Animais.
- Modificar retorno do metodo GetCommentsByAnimalId (não retornar os dados do animal)
- Implementar paginação ao retorno de Comentários.
- implementar histórico de endereços para Animais.


## **ROTAS**:
- saveAnimal: *Post* /api/animals/
- canUserEdit: *Get* /api/animals/{animalId}/{userKey} *Retorna true ou false*
- updateAnimal: *Put* /api/animals/{animalId}/{userKey}/edit
- addComment: *Post* /api/animals/{animalId}/comments
- getCommentsByAnimalId: *Get* /api/animals/{animalId}/comments 

