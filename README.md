# Projeto OO: Sistema de Folha de Pagamento

Refatoração do projeto do sistema Folha de Pagamento, disponível originalmente [aqui](https://github.com/marcio-henrique/Payroll).

## Code Smells

### Duplicated Code
- Código duplicado nos métodos `editEmployeeMenu` e `createEmployee` da classe `EmployeeController`
- Métodos construtores da classe `Employee` [Resolvido](#extract-class)
- Classes `TimeCard` e `AdditionalServiceTax` são idênticas, tanto em atributos, como em métodos
- Por consequência do item anterior, os métodos `addSaleResult` e `addTimeCard` são muito similares
- Método `menu` da classe `Menu`, onde diversas opções solicitam e fazem leitura do id do employee

### Long Parameter List
- No método `editEmployeeMenu` da classe `EmployeeController`, a chamada dos métodos construtores das classes `Hourly`,
`Commissioned` e `Salaried` recebem parâmetros de um único objeto ao invés do mesmo

### Long Method
- Método `menu` da classe `Menu` possui diversos tratamentos para as opções do menu - [Resolvido](#extract-method)
- Métodos `editEmployeeMenu` e `createEmployee` da classe `EmployeeController` possuem várias responsabilidades
- Métodos `addPaymentSchedule` e `editPaymentSchedule` da classe `PaymentController` possuem várias responsabilidades
- Método `isPayTime` da classe `PaymentEmployee` possue várias responsabilidades
- Método `payRoll` da classe `PaymentController` possue várias responsabilidades e alta complexidade
- Métodos `toString` das classes `PaymentSchedule`, `PaymentMethod` e `Employee` possuem tratamento de lógica - [Resolvido](#extract-method)

### Large Class
- Classe `Menu` possui métodos da lógica das funcionalidades de undo/redo - [Resolvido](#memento)

### Generative Speculation
- Diversos métodos não são utilizados, em especial métodos get/set e construtores vazios - [Resolvido](#removal-of-generative-speculation)
- O método `isLastWeekOfMonth` da classe `DateUtil` não é utilizado - [Resolvido](#removal-of-generative-speculation)
- Parâmetro id da classe `PaymentSchedule` não é utilizado - [Resolvido](#removal-of-generative-speculation)
- Importações de classes não utilizadas nas classes `Company` e `EmployeeSyndicate` - [Resolvido](#removal-of-generative-speculation)

## Refactoring

### Strategy
- Foi aplicado o DP Strategy na classe `PaymentSchedule` para solucionar problemas de complexidade no tratamento dos 
métodos de pagamento para os diferentes tipos de agenda

### Memento
- O DP Memento foi aplicado para prover as funcionalidades de undo/redo da Classe Company

### Extract Class
- Métodos `storeState` e `restoreState` da classe `Menu` foram extraídos para uma nova classe `ConvertUtil` além de terem
seus nomes aterados para, respectivamente, `company2string` e `string2company`
- Remoção da duplicação de construtores da classe `Employee`

### Extract Method
- Métodos de cada opção do método `menu` da classe `Menu` foram extraídos
- Tratamentos de lógica extraídos dos métodos `toString` das classes `PaymentSchedule`, `PaymentMethod` e `Employee`

### Introduce Parameter Object


### Removal of Generative Speculation
- Remoção dos métodos, parâmetros e importações não utilizados no projeto