
When we start the Graviton app, it initiates the boot-up process by providing dependencies for the Account Manager, Package Manager, and Service Manager.
* Account Manager: Provided with Ledger as strategy, it is responsible for posting services for users, maintaining logs in the ledger, calculating credit balances, and printing logs.
* Package Manager: Handles the creation of packages and provides package information.
* Service Manager: Utilizes the Service Factory as its dependency and is responsible for mapping services to the prices specified in the input file.

We also have utility processes including BootupProcess, FileReaderProcess, and FileWriterProcess.

When we invoke the Graviton app, it reads content from files specified through command-line arguments, which include information like price, purchase, and usage. After execution, it generates an OUTPUT file that summarizes the overall transactions for the user and their remaining balances.

The format for the files :
price.txt  ->  {PACKAGE/SERVICE}|{NAME}:{CREDITS}:{PRICE}
PACKAGE|BASIC:100:100
PACKAGE|STANDARD:250:225
PACKAGE|PREMIUM:500:450
SERVICE|S1:1
SERVICE|S2:2
SERVICE|S3:3
SERVICE|S4:99

purchase.txt  ->  {USER_NAME}|{PACKAGE_NAME}
PETER|BASIC
MADAN|PREMIUM
ALAN|STANDARD
ALAN|BASIC
SUFIYA|STANDARD
SANDY|BASIC

usage.txt  ->  {USER_NAME}|{SERVICE_NAME}
PETER|S1
MADAN|S2
SUFIYA|S2
SANDY|S1
SANDY|S1
SANDY|S4
ALAN|S1
MADAN|S3
SUFIYA|S3
ALAN|S1
RANDY|S4
SANDY|S1
SANDY|S1
SUFIYA|S3
