# IDEMIA-2020-STAGE-OTP
ONE TIME PASSWORD
Génération d’un code OTP et sauvegarde en base de données PostgreSQL avec le timestamp de génération et un status de WAITING 

1- pour générer un otp : http://localhost:8081/otp/generer

Validation d’un OTP reçu : une fois validé l’OTP doit passer au status USED, si l'OTP est expiré le statut doit passer au status DELETED

2-pour valider un otp : http://localhost:8081/otp/valider/OTP_NUM

3- return all OTPs : http://localhost:8081/getAll

Sécuriser l'application avec KeyCloak(olution open source pour l’identity/access management).
installation de keycloak : https://www.keycloak.org/downloads.html
Keycloak est démarré par défaut sur localhost:8080

configurations de keycloak :
1- Création d’un domaine : SpringBootKeycloack
2- Ajout des rôles : ADMIN et USER
3-Ajout d’un client : OTP
4-Mapping users to roles

