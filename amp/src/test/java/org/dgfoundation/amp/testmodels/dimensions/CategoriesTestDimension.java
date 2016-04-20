package org.dgfoundation.amp.testmodels.dimensions;

import static org.dgfoundation.amp.testmodels.dimensions.HNDNode.element;

import java.util.Arrays;
import java.util.List;

import org.dgfoundation.amp.testmodels.TestModelConstants;



public class CategoriesTestDimension extends HardcodedNiDimension {

	public final static CategoriesTestDimension instance = new CategoriesTestDimension("cats", TestModelConstants.CATEGORIES_DIMENSION_DEPTH);
	
	public CategoriesTestDimension(String name, int depth) {
		super(name, depth);
	}

	@Override
	protected List<HNDNode> buildHardcodedElements() {
		return Arrays.asList(
				element(2, "Accession Instrument", 
						element(5, "IPA" ), 
						element(6, "CARDS" ) ), 
					element(3, "Logframe", 
						element(7, "None" ), 
						element(8, "Outcome" ), 
						element(9, "Output" ), 
						element(45, "Activity" ) ), 
					element(388, "Activity Status", 
						element(2117, "default status" ), 
						element(2118, "second status" ) ), 
					element(5, "Document type", 
						element(46, "Document de Projet" ), 
						element(49, "Document d'Appel d'Offres" ), 
						element(50, "Devis / Estimation" ), 
						element(51, "Procédures" ), 
						element(53, "Termes de Reference" ), 
						element(54, "Contrat" ), 
						element(58, "Rapport Mensuel" ), 
						element(157, "Document de Programme" ), 
						element(158, "Document de Stratégie" ), 
						element(159, "Etude / analyse" ) ), 
					element(389, "Financing Instrument", 
						element(2120, "default financing instrument" ), 
						element(2125, "second financing instrument" ) ), 
					element(390, "Type of Assistance", 
						element(2119, "default type of assistance" ), 
						element(2124, "second type of assistance" ) ), 
					element(391, "Funding Status", 
						element(2089, "No Information" ), 
						element(2090, "Partially acquired" ), 
						element(2091, "Request sent no answer" ), 
						element(2092, "Totally acquired" ) ), 
					element(7, "Implementation Level", 
						element(69, "Provincial" ), 
						element(70, "National" ) ), 
					element(392, "Mode of Payment", 
						element(2093, "Cash" ), 
						element(2094, "Direct payment" ), 
						element(2095, "No Information" ), 
						element(2096, "Non-Cash" ), 
						element(2097, "Reimbursable" ) ), 
					element(8, "Team Type", 
						element(72, "Bilateral" ), 
						element(73, "Multilateral" ), 
						element(130, "Autre" ) ), 
					element(393, "Modalities", 
						element(2098, "Diplomats and courses" ), 
						element(2099, "Conferences, seminars, capacity specializations" ), 
						element(2100, "Interchanging models, proposals, and printed materials" ), 
						element(2101, "Country Experiences" ), 
						element(2102, "Internships" ), 
						element(2103, "Scholarships" ), 
						element(2104, "Virtual Platforms and blogs to consult, learn, and exchange ideas" ), 
						element(2105, "Videoconference and studying abroad" ), 
						element(2106, "Sending and exchanging experts, researchers, and professors" ), 
						element(2107, "Development of shared analytical studies" ), 
						element(2108, "Industry University Cooperation" ) ), 
					element(10, "Implementation Location", 
						element(76, "Country" ), 
						element(77, "Region" ), 
						element(78, "Zone" ), 
						element(79, "District" ), 
						element(250, "Sector Location" ), 
						element(252, "Groupement" ) ), 
					element(394, "Type of Cooperation", 
						element(2109, "Official Development Aid (ODA)" ), 
						element(2110, "Bilateral South South Cooperation" ), 
						element(2111, "Triangular South South Cooperation" ), 
						element(2112, "Regional South South Cooperation" ) ), 
					element(395, "Type of Implementation", 
						element(2113, "Program" ), 
						element(2114, "Project" ), 
						element(2115, "Action" ) ), 
					element(396, "Workspace Prefix", 
						element(2116, "SSC_" ) ), 
					element(397, "Budget Structure", 
						element(2121, "Salaries" ), 
						element(2122, "Operations" ), 
						element(2123, "Capital" ) ), 
					element(14, "Program Type", 
						element(98, "Secteur" ), 
						element(99, "Politique" ), 
						element(100, "Stratégie" ), 
						element(101, "Programme" ), 
						element(102, "Projet" ), 
						element(133, "Résultat" ), 
						element(134, "Actions" ) ), 
					element(398, "SSC Adjustment Type", 
						element(2126, "Official Development Aid" ), 
						element(2127, "Bilateral SSC" ), 
						element(2128, "Triangular SSC" ) ), 
					element(15, "Financial Instrument", 
						element(104, "Ressources propres Budget" ), 
						element(105, "Fonds PPTE" ), 
						element(106, "Ressources extérieures" ), 
						element(254, "Investissement direct" ) ), 
					element(399, "Project implementation mode", 
						element(2129, "Direct implementation through non-gov't entity" ), 
						element(2130, "Joint implementation through gov't and non-gov't entity" ), 
						element(2131, "National implementation throug gov't entity" ) ), 
					element(16, "MTEF Projection", 
						element(108, "projection" ), 
						element(109, "pipeline" ) ), 
					element(17, "A.C. Chapter", 
						element(152, "Budget" ), 
						element(153, "Dedicated" ), 
						element(154, "Project" ), 
						element(155, "Direct Project Funds (DPF)" ) ), 
					element(18, "Document Languages", 
						element(113, "English" ), 
						element(114, "French" ) ), 
					element(19, "Reference Documents", 
						element(115, "DSCRP" ), 
						element(116, "Programme de Gouvernement (2007-2011)" ), 
						element(117, "Cinq chantiers nationaux" ) ), 
					element(20, "Activity Level", 
						element(118, "Level 1" ), 
						element(119, "Level 2" ), 
						element(120, "Level 3" ) ), 
					element(21, "IPA activity categ", 
						element(140, "Fournitures" ), 
						element(141, "Services" ), 
						element(169, "Travaux construction" ), 
						element(171, "Rénumérations" ), 
						element(253, "Travaux réhabilitation" ) ), 
					element(22, "IPA Status", 
						element(143, "En préparation" ), 
						element(144, "En cours" ), 
						element(145, "Terminé" ) ), 
					element(23, "IPA Type", 
						element(2135, "IPA Type 1" ) ), 
					element(24, "Project Category", 
						element(147, "Government" ), 
						element(148, "Independent Structure Under Government Control/Supervision" ), 
						element(149, "Independent Executing Agency (not related to the Government)" ) ), 
					element(25, "IPA Activity Type", 
						element(156, "Activity 1" ) ), 
					element(26, "COMPOSANTS", 
						element(162, "Assistance technique/expertise" ), 
						element(163, "Assistance technique/étude et audits" ), 
						element(164, "Travaux" ), 
						element(165, "Fournitures" ), 
						element(167, "Fonctionnement" ), 
						element(172, "Assistance technique/formations, ateliers et conférences" ) ), 
					element(28, "Data Exchange", 
						element(179, "idml.status.Operationally Closed" ), 
						element(180, "idml.status.Advanced Authorization" ) ), 
					element(29, "Staff Information Type", 
						element(241, "Foreign" ), 
						element(242, "National" ) ), 
					element(30, "NGO Budget Type", 
						element(181, "Annual budget of internal/administrative functioning" ), 
						element(182, "Total Amount" ), 
						element(183, "Organization personal resources" ), 
						element(184, "Donors resources" ) ), 
					element(32, "Event type", 
						element(203, "Mission" ), 
						element(204, "Revue de Programme" ), 
						element(205, "Révision du Budget" ), 
						element(206, "Période d'Indisponibilité" ), 
						element(207, "Atelier ou Formation" ), 
						element(208, "Conférence ou Forum" ), 
						element(209, "Réunion" ) ), 
					element(33, "Colors", 
						element(191, "aqua" ), 
						element(192, "black" ), 
						element(193, "blue" ), 
						element(194, "fuchsia" ), 
						element(195, "silver" ), 
						element(196, "green" ), 
						element(197, "lime" ), 
						element(198, "maroon" ), 
						element(199, "navy" ), 
						element(200, "purple" ), 
						element(201, "teal" ), 
						element(202, "yellow" ) ), 
					element(34, "Procurement System", 
						element(210, "Use donor's own system" ), 
						element(211, "Use Nepalese system" ), 
						element(212, "Rely on other donor's system" ) ), 
					element(35, "Reporting System", 
						element(213, "Use donor's own system" ), 
						element(214, "Use Nepalese system" ), 
						element(215, "Rely on other donor's system" ) ), 
					element(36, "Audit System", 
						element(216, "Use donor's own system" ), 
						element(217, "Use Nepalese system" ), 
						element(218, "Rely on other donor's system" ) ), 
					element(37, "Institutions", 
						element(219, "Use donor's own structure" ), 
						element(220, "Create new parallel institution" ), 
						element(221, "Use existing Nepalese institutions" ), 
						element(222, "Rely on other donor's institutions" ) ), 
					element(38, "Phone Type", 
						element(223, "Home" ), 
						element(224, "Cell" ), 
						element(225, "Work" ) ), 
					element(39, "Contact Title", 
						element(261, "Dr" ) ), 
					element(40, "Pledges Types", 
						element(2137, "Education Pledge" ), 
						element(2138, "Healthcare Pledge" ), 
						element(2139, "Reconstruction Pledge" ) ), 
					element(41, "Indicator Source", 
						element(228, "RDC_BANQUE MONDIALE + AFRISTAT_Enquête 1-2-3, 2005" ), 
						element(229, "RDC_BANQUE MONDIALE_Renouveau du système éducatif: Priorités et alternatives_2005" ), 
						element(230, "RDC_DIAL_Enquête 1-2-3, 2005" ), 
						element(231, "RDC_DIVIPLAN/NK,Monographie Provinciale 2005" ), 
						element(232, "RDC_INS_Enquête 1-2-3, 2005" ), 
						element(233, "RDC_INS_Projections Démographiques 1999" ), 
						element(234, "RDC_MINISANTE, ELS/1998, Etat de lieu de secteur de la SANTE" ), 
						element(235, "RDC_Ministère de la santé, Annuaire Sanitaire Exercice 2004" ), 
						element(236, "RDC_Ministère de la Santé, Bulletin Epidémiologique de la RDC, Bilan Surveillance 2003" ), 
						element(237, "RDC_Ministère de la Santé, Plan Pluri Annuel Complet de la RDC, PEV mars 2007" ), 
						element(238, "RDC_UNICEF, PROJET MICS1/1995, Enquête nationale sur la situation des enfants et des femmes" ), 
						element(239, "RDC_UNICEF, PROJET MICS2/2001, Enquête nationale sur la situation des enfants et des femmes" ), 
						element(240, "RDC_UPPE-SRP_Enquête 1-2-3, 2005" ) ), 
					element(42, "Project Implementing Unit ", 
						element(255, "Parallel Project Implementing" ), 
						element(256, "Embedded Project Implementing" ), 
						element(257, "No Project Implementing Unit" ) ), 
					element(43, "Pledges Names", 
						element(2132, "ACVL Pledge Name 1" ), 
						element(2133, "ACVL Pledge Name 2" ), 
						element(2134, "Some third pledge name" ), 
						element(2136, "Pledge name 4" ) ), 
					element(44, "Activity Budget", 
						element(262, "Off Budget" ), 
						element(263, "On Budget" ) ), 
					element(45, "Workspace Group", 
						element(268, "Government" ), 
						element(269, "Line Ministries" ), 
						element(270, "Donor" ), 
						element(276, "Regional" ) ), 
					element(46, "Adjustment Type", 
						element(271, "Planned" ), 
						element(272, "Actual" ), 
						element(273, "Pipeline" ) ), 
					element(47, "Report Categories", 
						element(277, "On budget" ), 
						element(278, "Off budget" ) ), 
					element(48, "Transaction Type", 
						element(279, "Commitments" ), 
						element(280, "Disbursement Orders" ), 
						element(281, "Disbursements" ), 
						element(282, "Expenditures" ) ));
	}

}
