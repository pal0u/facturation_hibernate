-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 02 Avril 2014 à 07:37
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `facturationmf`
--
CREATE DATABASE IF NOT EXISTS `facturationmf` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `facturationmf`;

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE IF NOT EXISTS `adresse` (
  `idAdresse` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(10) NOT NULL,
  `rue` varchar(100) NOT NULL,
  `ville` varchar(100) NOT NULL,
  `zipCode` varchar(5) NOT NULL,
  `pays_idPays` int(11) NOT NULL,
  PRIMARY KEY (`idAdresse`),
  KEY `FK_o3pshmpp7efsl7igib5pkffr` (`pays_idPays`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `idCategorie` int(11) NOT NULL AUTO_INCREMENT,
  `nomCategorie` varchar(100) NOT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `dateNaissance` date NOT NULL,
  `nrTva` varchar(20) NOT NULL,
  `remise` double NOT NULL,
  `sexe` char(1) NOT NULL,
  `societe` varchar(100) NOT NULL,
  `adresse_idAdresse` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dgge447lhnbfdytxmlwqdtaba` (`adresse_idAdresse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `composite`
--

CREATE TABLE IF NOT EXISTS `composite` (
  `idComposite` int(11) NOT NULL AUTO_INCREMENT,
  `quantite` int(11) NOT NULL,
  `produit_idProduit` int(11) NOT NULL,
  PRIMARY KEY (`idComposite`),
  KEY `FK_qimfo0dlv3w5gqgo3her74dd3` (`produit_idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `counters`
--

CREATE TABLE IF NOT EXISTS `counters` (
  `CounterName` varchar(255) DEFAULT NULL,
  `CounterValue` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `detailcomposite`
--

CREATE TABLE IF NOT EXISTS `detailcomposite` (
  `produit_idProduit` int(11) NOT NULL,
  `composite_idComposite` int(11) NOT NULL,
  PRIMARY KEY (`produit_idProduit`,`composite_idComposite`),
  KEY `FK_k9nvfpu6k07gfsuxeor9535ni` (`composite_idComposite`),
  KEY `FK_k0su8pw7jooked2jhjy1brbpe` (`produit_idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `detaillot`
--

CREATE TABLE IF NOT EXISTS `detaillot` (
  `lot_idLot` int(11) NOT NULL,
  `facture_idFacture` int(11) NOT NULL,
  PRIMARY KEY (`facture_idFacture`,`lot_idLot`),
  KEY `FK_ox99vr48cq93odcy3fdye8qh9` (`facture_idFacture`),
  KEY `FK_4pmtdj5gjt9796yodchmi6lqw` (`lot_idLot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE IF NOT EXISTS `facture` (
  `idFacture` int(11) NOT NULL AUTO_INCREMENT,
  `dateFacture` date NOT NULL,
  `modePaiement` char(1) NOT NULL,
  `nrFacture` varchar(20) NOT NULL,
  `payer` int(11) NOT NULL,
  `tauxTva` double NOT NULL,
  `typeAchat` char(1) NOT NULL,
  `adresse_idAdresse` int(11) DEFAULT NULL,
  `client_idClient` int(11) NOT NULL,
  `vendeur_idVendeur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFacture`),
  UNIQUE KEY `UK_b1d2lrjgqml94uihds27rxqo` (`nrFacture`),
  KEY `FK_ghcocl22i9wg1rar1hfsu37ux` (`adresse_idAdresse`),
  KEY `FK_ox5ct6f0i1kk0c6kfmcltpl5d` (`client_idClient`),
  KEY `FK_j2t6c30gjpj3evaffp1pkwtt0` (`vendeur_idVendeur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `identification`
--

CREATE TABLE IF NOT EXISTS `identification` (
  `id` int(11) NOT NULL,
  `login` varchar(10) NOT NULL,
  `password` varchar(44) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lgqsrkvxdjetbaonavg8lss45` (`login`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `lot`
--

CREATE TABLE IF NOT EXISTS `lot` (
  `idLot` int(11) NOT NULL AUTO_INCREMENT,
  `quantite` int(11) NOT NULL,
  `produit_idProduit` int(11) NOT NULL,
  PRIMARY KEY (`idLot`),
  KEY `FK_936opf5yre0q72ao4q5aa2r1x` (`produit_idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE IF NOT EXISTS `pays` (
  `idPays` int(11) NOT NULL AUTO_INCREMENT,
  `nomPays` varchar(100) NOT NULL,
  PRIMARY KEY (`idPays`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `typeProduit` varchar(31) NOT NULL,
  `idProduit` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext NOT NULL,
  `libelle` varchar(100) NOT NULL,
  `prixHtva` double NOT NULL,
  `reference` varchar(100) NOT NULL,
  `stock` int(11) NOT NULL,
  `vignette` varchar(100) NOT NULL,
  `categorie_idCategorie` int(11) NOT NULL,
  PRIMARY KEY (`idProduit`),
  UNIQUE KEY `UK_27ds49lch5nxj0y5qa4dtf78j` (`reference`),
  KEY `FK_v7710nova4fvuunvjoxmn61q` (`categorie_idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `vendeur`
--

CREATE TABLE IF NOT EXISTS `vendeur` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `commission` float NOT NULL,
  `typeVendeur` varchar(255) DEFAULT NULL,
  `idChef` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kxemt5kbcpxy4j5twiltotc56` (`idChef`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `FK_o3pshmpp7efsl7igib5pkffr` FOREIGN KEY (`pays_idPays`) REFERENCES `pays` (`idPays`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_dgge447lhnbfdytxmlwqdtaba` FOREIGN KEY (`adresse_idAdresse`) REFERENCES `adresse` (`idAdresse`);

--
-- Contraintes pour la table `composite`
--
ALTER TABLE `composite`
  ADD CONSTRAINT `FK_qimfo0dlv3w5gqgo3her74dd3` FOREIGN KEY (`produit_idProduit`) REFERENCES `produit` (`idProduit`);

--
-- Contraintes pour la table `detailcomposite`
--
ALTER TABLE `detailcomposite`
  ADD CONSTRAINT `FK_k0su8pw7jooked2jhjy1brbpe` FOREIGN KEY (`produit_idProduit`) REFERENCES `produit` (`idProduit`),
  ADD CONSTRAINT `FK_k9nvfpu6k07gfsuxeor9535ni` FOREIGN KEY (`composite_idComposite`) REFERENCES `composite` (`idComposite`);

--
-- Contraintes pour la table `detaillot`
--
ALTER TABLE `detaillot`
  ADD CONSTRAINT `FK_4pmtdj5gjt9796yodchmi6lqw` FOREIGN KEY (`lot_idLot`) REFERENCES `lot` (`idLot`),
  ADD CONSTRAINT `FK_ox99vr48cq93odcy3fdye8qh9` FOREIGN KEY (`facture_idFacture`) REFERENCES `facture` (`idFacture`);

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `FK_j2t6c30gjpj3evaffp1pkwtt0` FOREIGN KEY (`vendeur_idVendeur`) REFERENCES `vendeur` (`id`),
  ADD CONSTRAINT `FK_ghcocl22i9wg1rar1hfsu37ux` FOREIGN KEY (`adresse_idAdresse`) REFERENCES `adresse` (`idAdresse`),
  ADD CONSTRAINT `FK_ox5ct6f0i1kk0c6kfmcltpl5d` FOREIGN KEY (`client_idClient`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `lot`
--
ALTER TABLE `lot`
  ADD CONSTRAINT `FK_936opf5yre0q72ao4q5aa2r1x` FOREIGN KEY (`produit_idProduit`) REFERENCES `produit` (`idProduit`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_v7710nova4fvuunvjoxmn61q` FOREIGN KEY (`categorie_idCategorie`) REFERENCES `categorie` (`idCategorie`);

--
-- Contraintes pour la table `vendeur`
--
ALTER TABLE `vendeur`
  ADD CONSTRAINT `FK_kxemt5kbcpxy4j5twiltotc56` FOREIGN KEY (`idChef`) REFERENCES `vendeur` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
