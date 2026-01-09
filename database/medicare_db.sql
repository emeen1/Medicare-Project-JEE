-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 09 jan. 2026 à 17:26
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `medicare_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
CREATE TABLE IF NOT EXISTS `app_user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `app_user`
--

INSERT INTO `app_user` (`username`, `password`, `role`) VALUES
('abdssamadnouam@gmail.com', '$2a$10$1dNMBc/vU22Hn22aRmubZuHvikxXYTGRnc4WtJ46VIoutK.BqsEPa', 'MEDECIN'),
('admin', '$2a$10$T8unalku6jIFgGH06I6OOutescS9Ks1JMh/NjbHblYBjLY4iwDeU.', 'ADMIN'),
('alaouimeriem@hopital.ma', '$2a$10$E94uTdiwP49u.SARpsDQIunDOfNVYpfROFvtyqM3f1PotvEyY87cW', 'MEDECIN'),
('amineaabaida@gmail.com', '$2a$10$zxNonGVZXYjMria28r8POOSCKba9C1ZOszp0GTSS8TWTDCbS1j58u', 'PATIENT'),
('bennanihind@gmail.com', '$2a$10$GJTOMez.y805.gm07.Gk2OoSACJ95XytKIiJioYElXPiP908vmDHG', 'MEDECIN'),
('bouazzaouiomar@gmail.com', '$2a$10$0GHHJNze7x4ovjsKQMRga..Btk7RKa6K9X4wJaC/pd/JpU4BPiwv.', 'MEDECIN'),
('bouchraahrich@gmail.com', '$2a$10$IBh6qfZVkbmWZBVRlveD0ObvNuHrrxSrJTimP40g7JEnrnkrEzpBG', 'PATIENT'),
('brahimelbouanani@gmail.com', '$2a$10$3.wdMBH9mn5.5tIJMftW8uSKioc4jS1om/1.7rbZAhwDkeyBk4Zti', 'PATIENT'),
('dr.alami', '$2a$10$0.LysKbFvoEtlZrF8xv2hu2uMyY9pkee8PL0GHWXe1v/Za357Xwba', 'MEDECIN'),
('dr.bennani', '$2a$10$KvjNzD6wcUjAzKQ/EyenzOORgW29BQS1vqW6Ti7ImIw7U1d6jmz5q', 'MEDECIN'),
('imanenouam@gmail.com', '$2a$10$H6Lq20w4IcgiZPd6BapiQepMAjBwE20yF2NBzHSVROFL9Xg9NUQVC', 'PATIENT'),
('karim@gmail.com', '$2a$10$lxlK5luD1bI7q24ryMpPQ.Ao1YRnMyGZIQpe9JD9RSwU9feY3cDme', 'PATIENT'),
('malik@gmail.com', '$2a$10$jKNwK5vKHvfTgZFHBUbH6Ofa5L7jATAjhP2dW8ICk8I2PTuuzbsUW', 'MEDECIN'),
('mouhsinehanin@gmail.com', '$2a$10$KSmlE6JmwjmwwilntjbfU.MTMMo6dVpV4vj8vswT0MEG9fsjPFp4.', 'PATIENT'),
('saadaniansari@gmail.com', '$2a$10$zZtK.REGlQHjnCufG/rpWut7Urih8uaLLxbuZAYPvgp/aekrvVwkW', 'PATIENT'),
('salma@gmail.com', '$2a$10$ePce5ww/B8lwLpRwr8NBu.oyDx9GAYxKYJlNhDGz/M3N.jms5GNKa', 'PATIENT'),
('salmasalami@gmail.com', '$2a$10$HrvGpohItyMJblvuqwj63ObU4VV1f8GNZzWrroDtNL1KJ09nYDCby', 'PATIENT'),
('touhamighita@gmail.com', '$2a$10$wervn5hZWRn/foKibcp9feiaipx95TFY11SLAEvWF6HnlWhP4oXGa', 'MEDECIN'),
('zahidkhalid@gmail.com', '$2a$10$wZZSN9SC0Jsz.BT2t2zeXOKokHYP5Z9u0a1ETUd0d10IyixFg3V3u', 'MEDECIN');

-- --------------------------------------------------------

--
-- Structure de la table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
CREATE TABLE IF NOT EXISTS `consultation` (
  `date_consultation` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rendez_vous_id` bigint DEFAULT NULL,
  `rapport` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKex6w9vcy46v5ygg7qn8km1ku` (`rendez_vous_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `consultation`
--

INSERT INTO `consultation` (`date_consultation`, `id`, `rendez_vous_id`, `rapport`) VALUES
('2026-01-09 17:38:18.455000', 1, 1, 'Le patient présente une hypertension légère (14/9). \nRecommandations : Régime pauvre en sel, activité physique 30min/jour. \nPrescription : Amlodipine 5mg (1 comprimé le matin pendant 1 mois). \nProchain contrôle dans 4 semaines.'),
('2026-01-09 17:45:55.574000', 2, 8, 'PRESCRIPTION :\r\nAugmentin 1g (Amoxicilline/Acide clavulanique) : 1 comprimé à prendre 3 fois par jour (matin, midi et soir) au début des repas, pendant une durée de 7 jours consécutifs.\r\nDoliprane 1000mg (Paracétamol) : 1 gélule à prendre toutes les 6 heures uniquement en cas de fièvre supérieure à 38.5°C ou de douleurs (Maximum 4 gélules par 24 heures).\r\nSirop Hélicidine : 1 cuillère à soupe 3 fois par jour en cas de toux sèche persistante.\r\nVitamines C 500mg : 1 comprimé effervescent le matin au petit-déjeuner pendant 10 jours.\r\nCONSEILS MÉDICAUX :\r\nRepos strict à domicile pendant 3 jours.\r\nHydratation abondante (minimum 2 litres d\'eau par jour).\r\nSurveillance de la température matin et soir.\r\nRevenir en consultation si la fièvre persiste au-delà de 48 heures ou en cas d\'apparition de difficultés respiratoires.'),
('2026-01-09 18:03:29.305000', 3, 9, 'PRESCRIPTION (Dosages selon le poids de l\'enfant) :\r\nDoliprane 2,4% (Paracétamol) - Suspension buvable :\r\nAdministrer une dose-poids (graduation correspondant au poids de l\'enfant sur la pipette) toutes les 6 heures en cas de fièvre supérieure à 38,5°C ou de douleur manifeste. Maximum 4 prises par 24 heures.\r\nSérum Physiologique (Dosettes unitaires) :\r\nLavage de nez (DRP) systématique avant chaque repas et avant chaque sieste (minimum 6 fois par jour). Bien incliner la tête de l\'enfant sur le côté pour évacuer les sécrétions.\r\nCamilia (Homéopathie) :\r\n1 dosette 3 fois par jour par voie orale, pendant 3 à 5 jours, pour soulager l\'irritabilité liée à la poussée dentaire.\r\nBaume Gingival (type Delabarre ou Dolodent) :\r\nAppliquer une noisette sur la gencive douloureuse 2 fois par jour en massant doucement avec un doigt propre.');

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

DROP TABLE IF EXISTS `medecin`;
CREATE TABLE IF NOT EXISTS `medecin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `specialite` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`id`, `email`, `nom`, `specialite`, `username`) VALUES
(1, 'alami@clinique.ma', 'Dr. Ahmed Alami', 'Cardiologue', 'dr.alami'),
(2, 'bennani@clinique.ma', 'Dr. Sara Bennani', 'Chirurgien Dentiste', 'dr.bennani'),
(3, 'tazi@clinique.ma', 'Dr. Youssef Tazi', 'Médecin Généraliste', NULL),
(4, 'bouazzaouiomar@gmail.com', 'Dr.Bouazzaoui	Omar', 'Chirurgien Orthopédique', 'bouazzaouiomar@gmail.com'),
(5, 'bennanihind@gmail.com', 'Dr.Bennani Hind', 'Pédiatre', 'bennanihind@gmail.com'),
(6, 'alaouimeriem@hopital.ma', 'Dr.Alaoui Meriem', 'Gynécologue', 'alaouimeriem@hopital.ma'),
(7, 'malik@gmail.com', 'Dr.Achraf Malik', 'Cardiologue', 'malik@gmail.com'),
(9, 'touhamighita@gmail.com', 'Dr.Touhami Ghita', 'ORL', 'touhamighita@gmail.com'),
(10, 'abdssamadnouam@gmail.com', 'Dr.Abdssamad Nouam', 'Pédiatre', 'abdssamadnouam@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `email`, `nom`, `username`) VALUES
(1, 'karim@gmail.com', 'Karim Idrissi', 'karim@gmail.com'),
(2, 'salma@gmail.com', 'Salma Benkirane', 'salma@gmail.com'),
(3, 'omar.fassi@hotmail.com', 'Omar Fassi', NULL),
(4, 'amineaabaida@gmail.com', 'amine aabaida', 'amineaabaida@gmail.com'),
(5, 'brahimelbouanani@gmail.com', 'Brahim elbouanani', 'brahimelbouanani@gmail.com'),
(6, 'bouchraahrich@gmail.com', 'Bouchra ahrich', 'bouchraahrich@gmail.com'),
(7, 'mouhsinehanin@gmail.com', 'mouhsine hanin', 'mouhsinehanin@gmail.com'),
(8, 'saadaniansari@gmail.com', 'Saadani ansari', 'saadaniansari@gmail.com'),
(9, 'salmasalami@gmail.com', 'salma salami', 'salmasalami@gmail.com'),
(10, 'imanenouam@gmail.com', 'Imane Nouam', 'imanenouam@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `rendez_vous`
--

DROP TABLE IF EXISTS `rendez_vous`;
CREATE TABLE IF NOT EXISTS `rendez_vous` (
  `date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `medecin_id` bigint DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  `status` enum('CANCELED','DONE','PENDING') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3cfgw39eujld73uwqc3efmom2` (`medecin_id`),
  KEY `FK35ayulwe26jii3vq14v6sokp3` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `rendez_vous`
--

INSERT INTO `rendez_vous` (`date`, `id`, `medecin_id`, `patient_id`, `status`) VALUES
('2026-01-08 17:38:18.435000', 1, 1, 1, 'DONE'),
('2026-01-10 17:38:18.463000', 2, 1, 2, 'PENDING'),
('2026-01-09 17:38:18.480000', 3, 2, 3, 'CANCELED'),
('2026-01-16 17:38:18.489000', 4, 2, 1, 'DONE'),
('2026-02-05 09:30:00.000000', 5, 9, 8, 'DONE'),
('2026-01-09 17:30:00.000000', 6, 5, 9, 'PENDING'),
('2026-01-28 09:30:00.000000', 7, 9, 9, 'CANCELED'),
('2026-01-09 17:44:00.000000', 8, 7, 5, 'DONE'),
('2026-01-10 09:02:00.000000', 9, 10, 10, 'DONE');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `FKjglbsx1peh4uiw2l9b3cr853w` FOREIGN KEY (`rendez_vous_id`) REFERENCES `rendez_vous` (`id`);

--
-- Contraintes pour la table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  ADD CONSTRAINT `FK35ayulwe26jii3vq14v6sokp3` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `FK3cfgw39eujld73uwqc3efmom2` FOREIGN KEY (`medecin_id`) REFERENCES `medecin` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
