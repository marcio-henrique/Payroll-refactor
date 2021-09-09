# Projeto OO: Sistema de Folha de Pagamento

Refatoração do projeto do sistema Folha de Pagamento, disponível originalmente [aqui](https://github.com/marcio-henrique/Payroll).

##Code Smells

###Duplicated Code
- Código duplicado nos métodos `editEmployeeMenu` e `createEmployee` da classe `EmployeeController`
- Métodos da construtores da classe `Employee`
- Classes `TimeCard` e `AdditionalServiceTax` são idênticas, tanto em atributos, como em métodos
- Por consequência do item anterior, os métodos `addSaleResult` e `addTimeCard` são muito similares
- Método `menu` da classe `Menu`, onde diversas opções solicitam e fazem leitura do id do employee

###Long Parameter List
- No método `editEmployeeMenu` da classe `EmployeeController`, a chamada dos métodos construtores das classes `Hourly`,
`Commissioned` e `Salaried` recebem parâmetros de um único objeto ao invés do mesmo

###Long Method
- Método `menu` da classe `Menu` possui diversos tratamentos para as opções do menu
- Métodos `editEmployeeMenu` e `createEmployee` da classe `EmployeeController` possuem várias responsabilidades
- Métodos `addPaymentSchedule` e `editPaymentSchedule` da classe `PaymentController` possuem várias responsabilidades
- Método `isPayTime` da classe `PaymentEmployee` possue várias responsabilidades
- Método `payRoll` da classe `PaymentController` possue várias responsabilidades e alta complexidade
- Método `toString` da classe `PaymentSchedule` possui tratamento de lógica

###Large Class
- Classe `Menu` possui métodos da lógica das funcionalidades undo/redo

###Generative Speculation
- Diversos métodos não são utilizados, em especial métodos get/set e construtores vazios
- O método `isLastWeekOfMonth` da classe `DateUtil` não é utilizado
- Parâmetro id da classe `PaymentSchedule` não é utilizado
- Importações de classes não utilizadas nas classes `Company` e `EmployeeSyndicate`

###Feature Envy


##Refactoring

###Strategy
- Foi aplicado o DP Strategy na classe `PaymentSchedule` para solucionar problemas de complexidade no tratamento dos 
métodos de pagamento para os diferentes tipos de agenda

###Removal of Generative Gpeculation
- Remoção dos métodos, parâmetros e importações não utilizados no projeto