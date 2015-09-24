# Travaux Pratiques
# SOA : Architecture et Intégration d’Applications d’Entreprise

![Zenika](ressources/logo-zenika.jpg)


## Sommaire
- 1.Les Web Services
	- TP1 – Développer un premier Web Service
	- TP2 – Développer un Web Service en "WSDL-First"
	- TP3 – REST
- 2.Les MOM
	- TP4 – Prise en main du broker ActiveMQ
	- TP5 – Client JMS Java
	- TP6 – Bonus : AMQP
- 3.Les ESB
	- TP5 – Prise en main du framework Camel
	- TP6 – Aller plus loin avec Camel9ES
	- TP7 – Les EIP avec Camel
- 4.BPEL
	- TP8 – Installation du moteur BPEL et de l’IDE
	- TP9 – Hello BPEL !
	- TP10 – Comparateur de tarifs de billets d’avion
	- TP11 – Bonus : Processus durables14

## Configuration pour les TPs
Eclipse Mars (jee edition)
Apache CXF 3.1.2
Java 8
Tomcat 8	
Liberty 8.5
SoapUI

	
## Les Web Services

## TP1 – Développer un premier Web Service

### Objectif

Ce premier TP va nous permettre de mettre en oeuvre un premier Web Service et d'installer l'environnement de développement.
L'objectif métier de ce service est de fournir un message d'accueil à l'adresse des partenaires se connectant au SI Resanet.

### Développement d'un POJO

- Dezipper le fichier CXF
- Créer un nouveau projet Java sous Eclipse (Dynamic Web Project) et cocher la case générer le fichier web.xml
- Sous Eclipse, aller dans Windows->Préférences-> Web Service configurer le path de CXF
- Sous Eclipse, aller dans Windows->Préférences->Servers->Runtime Environment installer Tomcat 8.
- Sous Eclipse, click droit sur le projet nouvellement créé, aller sur Préférences->Project Facet et cocher CXF.
- Sous Eclipse, click droit sur le projet nouvellement créé, aller sur Préférences->Targeted runtime et choississez Tomcat 8.
- Créer une nouvelle "User Library" CXF contenant l'ensemble des jar présents dans le répertoire lib de CXF et ajouter cette bibliothèque au classpath du nouveau projet
- Créer une interface com.resanet.ws.AccueilService contenant une fonction
	- afficherMessage prenant le nom du partenaire (ie. String) en paramètre d'entrée et renvoyant un message (ie. String) en sortie
- Créer une classe com.resanet.ws.AccueilServiceImpl implémentant l'interface précédente 
et renvoyant le message "RESANET : Bienvenue [nom_partenaire]"

### Mise en place du Web Service

- Ajouter l'annotation @WebService à l'interface nouvellement créée (sans paramètre) 
ainsi qu'à la classe l'implémentant (paramètres :endpointInterface="com.resanet.ws.AccueilService") : 
le Web service est prêt à être publié
- Créer une classe com.resanet.ws.ServerAccueil contenant une fonction main avec le contenu suivant :

```
System.out.println("Serveur démarré...");
AccueilService service =new AccueilServiceImpl();
String address ="http://localhost:9000/accueil";
Endpoint.publish(address, service);
```

- Démarrer la classe (le main lance un serveur Jetty)

### Appeler le Web Service

Nous allons appeler notre Web Service de 2 façons suivantes : 
		- Client SOAP 
		- programme Java.Client soapUI

- Installer et lancer soapUI
- Créer un nouveau projet TP1 et fournir l'adresse suivante (ie.WSDL initial) : http://localhost:9000/accueil?wsdl
- Déplier l'ensemble du projet et lancer la requête Request 1 avec le nom 
d'un partenaire fictif comme paramètre d'entrée (ie. remplacer le caractère?)
- Vérifier que le contenu de la réponse est correct Client Java

- Créer une classe com.resanet.ws.ConsumerAccueil contenant le code suivant :

```
URL wsdlURL =newURL("http://localhost:9000/accueil?wsdl");
QName serviceName =new QName("http://ws.resanet.com/","AccueilServiceImplService");
Service service = Service.create(wsdlURL, serviceName);
AccueilService client = service.getPort(AccueilService.class);
System.out.println(client.afficherMessage("Air France"));
```

- Lancer le programme
- Vérifier que le contenu de la réponse est correct

## TP2– Développer un Web Service en "WSDL-First"

### Objectif

L'objectif de ce TP est développer un Web Service via la méthode "WSDL-First". 
Ce service permettra d'ajouter et de lire les commentaires associés à un hôtel (il sera de type"Document/Literal Wrapped")

### Initialisation du WSDL

- Créer un projet Web sous Eclipse
- Configurer un serveur Tomcat sous Eclipse et y ajouter le projet
- Copier les fichiers commentaire.wsdl et commentaire.xsd dans le répertoire WebContent/wsdl
- Compléter le fichier commentaire.xsd en définissant :
	- un elément ajouter contenant le nom d'un hôtel et un commentaire associé
	- un elément lister contenant le nom d'un hôtel
	- un elément listerResponse contenant l'ensemble des commentaires associés (cf. maxOccurs="unbounded")
	- un élément commentaireFault contenant un code de type entier et une description
- Compléter le fichier commentaire.wsdl en définissant :
	- une opération ajouter(Literal Wrapped/SOAP 1.1/One-Way) permettant d'ajouter un commentaire à un hôtel 
	(si l'hôtel est inconnu, il est créé)
	- une opération lister(Literal Wrapped/SOAP 1.1/Request-Response) permettant de récupérer l'ensemble des commentaires d'un hôtel (si l'hôtel est inconnu, une fault est renvoyée)
	
### Implémentation du Web Service

- Générer l'ensemble des classes Java à partir du fichier WSDL
- Implémenter le service (utiliser une Map<String, <String>List> afin de simuler une base de données d'hôtels : String étant le nom de l'hôtel)
- Tester le service via soapUI et/ou un consommateur JavaWS

## TP2– WSDL avancée

L'objectif du TP est de sécuriser un webservice avec ws-security et utiliser ws-attachment (profil avancé sur ws-*).
Ce webservice sera un module de gestion de personne et comportera deux méthodes/opérations:
- affichePersonne retourne quelques informations d'une personne
- detailPersonne() retourne toutes les informations de la personne

Etapes 1 : Activer MTOM
- Créer un projet Web sous Eclipse
- Configurer un serveur Tomcat sous Eclipse et y ajouter le projet
- Créer un POJO Java Personne qui comportera les attributs suivant:
	- nom (chaine de caractère)
	- prenom (chaine de caractère)
	- age (un numéric)
	- sexe (un caractère)
	- photo (un dataHandler)

- Développer un webservice qui mettra à disposition 2 opérations
	- affichePersonne prend en argument un nom et retourne le nom, prénom, et l'âge de la personne.
	- detailPersonne prend en argument un nom et retourne tous les attributs de la personne.
- Construire un jeu de donnée.
- Activer le protocole MTOM
- Développer les méthodes 
- Lancer le serveur Tomcat
- Tester avec SoapUI. Observez-vous une différence dans le WSDL généré ? Si oui laquelle.
- Arreter le serveur

Etapes 2 : Sécuriser le point d'accès
- Sécuriser vos l'URL en utilisant les contraintes de sécurités
- Une fois fini, publier sur Tomcat et tester avec SOAPUI.

Etapes 3 : Tester les habilitations dans un contexte JEE

Dans un contexte JEE, il faut récupérer le rôle de l'utilisateur connecté. Cela peut se faire en utilisant le contexte de l'application 'WebServiceContext'.
Dans note cas on utilisera :

```
	// cas d'utilisation dans le cadre des ejb
	@Resource
	private SessionContext sessionContext;
```

- Télécharger Liberty Profil 8 
- Modifier le fichier server.xml pour y ajouter les lignes suivantes :

```
<server>
	<!-- Enable features -->
	<featureManager>
		<feature>jsp-2.2</feature>
		<feature>ejbLite-3.1</feature>
		<feature>cdi-1.0</feature>
		<feature>jaxb-2.2</feature>
		<feature>jndi-1.0</feature>
		<feature>jdbc-4.0</feature>
		<feature>jpa-2.0</feature>
		<feature>jsf-2.0</feature>
		<feature>jmsMdb-3.1</feature>
		<feature>managedBeans-1.0</feature>
		<feature>wasJmsServer-1.0</feature>
		<feature>wasJmsClient-1.1</feature>
		<feature>servlet-3.0</feature>
		<feature>webProfile-6.0</feature>
		<feature>beanValidation-1.0</feature>
		<feature>ldapRegistry-3.0</feature>
		<feature>appSecurity-2.0</feature>
		<feature>localConnector-1.0</feature>
		<feature>appSecurity-2.0</feature>
		<feature>wsSecurity-1.1</feature>
		<feature>jaxrs-1.1</feature>
		<feature>wmqJmsClient-1.1</feature>
	</featureManager>

	<httpEndpoint host="localhost" httpPort="9081" httpsPort="9443" id="defaultHttpEndpoint"/>

	<basicRegistry id="simple" realm="BasicRegistry">
		<group name="TEST_WS_ALL">
			<member name="m"/>
			<member name="a"/>			
		</group>
		<group name="TEST_WS_CONSULT">
			<member name="m"/>
			<member name="c"/>
			<member name="w"/>			
		</group>
		<group name="TEST_WS_WRITE">
			<member name="m"/>
			<member name="w"/>			
		</group>
		<user name="a" password="a"/>
		<user name="c" password="c"/>
		<user name="w" password="w"/>		
		<user name="m" password="m"/>				
		<user name="z" password="z"/>						
	</basicRegistry>

	<applicationMonitor updateTrigger="mbean"/>
<server>	
```

- Déployer l'ear "prios-ws-TP4-ear.ear" qui se trouve dans le répertoire TP2Bonus
en rajoutant la ligne suivante en modifiant l'attribut location:
```    
	<enterpriseApplication id="prios-ws-TP4-ear" location="XXXXXXXXXX/prios-ws-TP4-ear.ear" name="prios-ws-TP4-ear"/>
```

- tester avec SOAPUI



## TP3– REST

### Objectifs

L'objectif de ce TP est mettre en oeuvre un service de gestion de voyages de type REST

### Installation du service

- Importer le projet <WS-T3> dans Eclipse
- Le service de gestion des voyages fournit les opérations suivantes
	- créer un voyage
	- lire un voyage
	- lister les voyages
	- mettre à jour un voyage
	- supprimer un voyage
- Compléter la configuration CXF (ie. cxf-servlet.xml) et annoter l'ensemble des méthodes afin de fournir un service REST
- Tester le service à l'aide de SoapUI

### Service de recherche

- Modifier l'opération "lister les voyages" afin qu'elle puisse prendre en charge un paramètre permettant de chercher les voyages ayant un libellé donné
- Tester

## Les MOM

## TP4 – Prise en main du broker ActiveMQ

### Objectif

Prise en main du produit Apache ActiveMQ

### Consignes

- Décompresserle broker Apache ActiveMQ
- Se connecter sur la console d'administration (http://localhost:8161/)
- Créer une file nommée « mom.tp1 »
- Envoyer un message quelconque sur cette file
- Afficher le message MOM

## TP5 –Client JMS Java

### Objectif

Découverte des API JMS producer et consumer.

### Client JMS simple

- Créer un projet Java classique
- Importer le driver ActiveMQProducer

- Développer un client Java capable d’envoyer un message JMS sur une file
- Envoyer un header JMS (au choix) avec le messageConsumer

- Développer un client Java capabledelire sur une file JMS
- Afficher le contenu du message, ainsi que ses headers JMS

### Message Driven

- Développeret configure un composant Spring MessageListener afin de recevoir des messages JMS de façon asynchrone 
(MDP : Message Driven POJO)

## TP6 – Bonus : AMQP

### Objectif

Utilisation du standard de messaging AMQP

### Installation

- Installer le moteur ERLANG
- Installer le broker RabbitMQ
- Lancer et verifier que tout fonctionne3.Producer / consumer

- Développer un producer AMQP
- Développer un consumer AMQP

## Les ESB

## TP7 –Prise en main du framework Camel

### Objectif

Mettre en place un premier conteneur de services découvrant:

- Le composant *camel-file*
- Découverte du fonctionnement de Camel

### Mise en place

- Mettre en place une route avec 2 endpoints à l 'aide de l'API XML :
	- file consumer : scrute le répertoire <in> toutes les 10 secondes
	- file provider : copie le fichier reçu dans le répertoire <out>
- Lancer le programme et vérifier le bon fonctionnement avec un test

### Bonus

- Développer la même route avec le DSL Java

## TP8 –Aller plus loin avec Camel

### Mise en place

- Faire évoluer l’exercice précédent, en remplaçant le file poller par un consommateur JMS via le endpoint came-jms.
- Les messages pourront soit être envoyés via la console ActiveMQ, ou via un client Java.


## TP9 – Les EIP avec Camel

### Objectif
Implémentation des EIP dans Camel

### Ma première route Camel

- On utilisera le composant JMS en mode consumer, la brique EIP Pipe et le composant File en mode producer

![](ressources/images/camel_1.png)

### Content Based Router

- Implémenter l'EIP CBR se basant sur un header JMS nommé type *Message*
- On utilisera le component Camel JMS, la brique EIP Content BasedRouter pour transférer les messages vers 2 autres files JMS (choix basé sur le header).

![](ressources/images/camel_2.png)

### Multicast

- Implémenter l'EIP Multicast Camel en parallèle
- On injectera directement un message de test dans la brique Multicast, et on affichera à l'écran les messages de sorties.

![](ressources/images/camel_3.png)

### Message translator

- Implémenter l'EIP Message Translator
- On souhaite ajouter « World ! » au message d'entrée « Hello »
- On injectera directement les messages de tests dans l'implémentation de l'EIP, et on affichera à l'écran les messages de sorties.

