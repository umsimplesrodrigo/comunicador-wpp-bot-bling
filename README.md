# A simple pont between API Zap Work and API Bling
## Problem
In my work there is a problem, my superior needs to manually create and send accounts receivable to his client list.
## Solution
Link the finacial control system with the whatsapp service system. And i'am doing that using API of both systems.
## How does it work?
- **Authentication with API Bling**
  - [Documentation](https://developer.bling.com.br/docs)
- **Authentication with API Zap Woek**
  - [Documentation is private](https://github.com/umsimplesrodrigo/comunicador-wpp-bot-bling)
- **Order flow**
  - User selects `Financial` option on whatsapp bot
  - Select a duplicate payment
  - Program receive whatsapp bot request via API
  - Search the customer for the CNPJ in the **Bling** database via API
