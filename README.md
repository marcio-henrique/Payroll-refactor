# Projeto OO: Sistema de Folha de Pagamento

Refatoração do projeto do sistema Folha de Pagamento, disponível originalmente [aqui](https://github.com/marcio-henrique/Payroll).

## Code Smells

### Duplicated Code
- Código duplicado nos métodos `editEmployeeMenu` e `createEmployee` da classe `EmployeeController`
- Métodos construtores da classe `Employee` - [Resolvido](#extract-class)
- Classes `TimeCard` e `AdditionalServiceTax` são idênticas, tanto em atributos, como em métodos
- Por consequência do item anterior, os métodos `addSaleResult` e `addTimeCard` são muito similares
- Método `menu` da classe `Menu`, onde diversas opções solicitam e fazem leitura do id do employee

### Long Parameter List
- No método `editEmployeeMenu` da classe `EmployeeController`, a chamada dos métodos construtores das classes `Hourly`,
`Commissioned` e `Salaried` recebem parâmetros de um único objeto ao invés do mesmo - [Resolvido](#introduce-parameter-object)

### Long Method
- Método `menu` da classe `Menu` possui diversos tratamentos para as opções do menu - [Resolvido](#extract-method)
- Métodos `editEmployeeMenu` e `createEmployee` da classe `EmployeeController` possuem várias responsabilidades 
- Métodos `addPaymentSchedule` e `editPaymentSchedule` da classe `PaymentController` possuem várias responsabilidades - [Resolvido](#extract-method)
- Método `isPayTime` da classe `PaymentEmployee` possue várias responsabilidades - [Resolvido](#strategy)
- Método `payRoll` da classe `PaymentController` possue várias responsabilidades e alta complexidade - Resolvido: [Strategy](#strategy) e [Extract Method](#extract-method)
- Métodos `toString` das classes `PaymentSchedule`, `PaymentMethod` e `Employee` possuem tratamento de lógica - [Resolvido](#extract-method)

### Large Class
- Classe `Menu` possui métodos da lógica das funcionalidades de undo/redo - Resolvido: [Memento](#memento) e [Extract Class](#extract-class)
- Classe `PaymentController` trata tanto das `PaymentSchedule` quanto da `payroll` - [Resolvido](#extract-class)

### Generative Speculation
- Diversos métodos não são utilizados, em especial métodos get/set e construtores vazios - [Resolvido](#removal-of-generative-speculation)
- O método `isLastWeekOfMonth` da classe `DateUtil` não é utilizado - [Resolvido](#removal-of-generative-speculation)
- Parâmetro id da classe `PaymentSchedule` não é utilizado - [Resolvido](#removal-of-generative-speculation)
- Importações de classes não utilizadas nas classes `Company` e `EmployeeSyndicate` - [Resolvido](#removal-of-generative-speculation)

## Refactoring

### Strategy
- Foi aplicado o DP Strategy na classe `PaymentSchedule` para solucionar problemas de complexidade no tratamento dos 
métodos de pagamento para os diferentes tipos de agenda: Cada agenda agora possui uma classe própria, onde implementam os métodos
`getMonthlyDiv` e `isDateToPay`, definidos na interface `ScheduleStrategy`.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/78d38907677879eda8d718bde72f2829bd60b5e1),
[Link das Classes do Strategy](https://github.com/marcio-henrique/Payroll-refactor/tree/main/src/model/Payment/Schedule)

### Memento
- O DP Memento foi aplicado para prover as funcionalidades de undo/redo da Classe Company: Os métodos de salvar estado
atual, undo e redo são de responsabilidade da classe `CaretakerCompany`, enquanto o estado é salvo como um objeto da classe
`Memento Company`.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/4f97bfa92d7a89ea1d3963ff19a08530f2dfbffd),
[Link das Classes do Memento](https://github.com/marcio-henrique/Payroll-refactor/tree/main/src/model/Company/Memento)

### Extract Class
- Métodos `storeState` e `restoreState` da classe `Menu` foram extraídos para uma nova classe `ConvertUtil` além de terem
seus nomes aterados para, respectivamente, `company2string` e `string2company`.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/4f97bfa92d7a89ea1d3963ff19a08530f2dfbffd)
- Métodos `addPaymentSchedule` e `editPaymentSchedule` da classe `PaymentController` foram extraídos para uma nova classe
`ScheduleController`.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/70425556c937525350bc48ffaffe5d38c50bfc0f)
- Remoção da duplicação de construtores da classe `Employee`.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/302cbbbc3d8397b882aeebf94713f5ea20cf5e4f)

### Extract Method
- Métodos de cada opção do método `menu` da classe `Menu` foram extraídos
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/f20a69e61c0da98593a5e1d2b00a62894a0741ca)
- Tratamentos de lógica extraídos dos métodos `toString` das classes `PaymentSchedule`, `PaymentMethod` e `Employee`.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/f20a69e61c0da98593a5e1d2b00a62894a0741ca)
- Método `payRoll` da classe `PaymentController` foi dividido em diversos métodos, de acordo com suas responsabilidades.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/44d4514f940f69b56ab158eee603a5c5ec54a2b0)
- Métodos `addPaymentSchedule` e `editPaymentSchedule` (agora) da classe `ScheduleController` foram divididos em diversos
métodos, de acordo com suas responsabilidades.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/70425556c937525350bc48ffaffe5d38c50bfc0f)

### Introduce Parameter Object
- Adição de construtores nas classes `Employee`, `Hourly`,
`Commissioned` e `Salaried` com objeto `Employee` como Parâmetro.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/b4c261818537b6ff50cbb5d790adee4080aea64d)

### Removal of Generative Speculation
- Remoção dos métodos, parâmetros e importações não utilizados no projeto.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/6381afef79e965bcc42fd22a2a8e184d40cded91)

### Hide Delegate
- Criação dos métodos `isCommissioned`, `isSalaried` e `isHourly` para ocultar a chamada dos métodos `.getClass().isAssignableFrom()`.
[Link do Commit](https://github.com/marcio-henrique/Payroll-refactor/commit/44d4514f940f69b56ab158eee603a5c5ec54a2b0)
