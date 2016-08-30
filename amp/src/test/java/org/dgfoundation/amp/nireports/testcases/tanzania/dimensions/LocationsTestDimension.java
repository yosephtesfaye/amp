package org.dgfoundation.amp.nireports.testcases.tanzania.dimensions;

import static org.dgfoundation.amp.nireports.testcases.HNDNode.element;

import java.util.Arrays;
import java.util.List;

import org.dgfoundation.amp.nireports.testcases.HNDNode;
import org.dgfoundation.amp.nireports.testcases.HardcodedNiDimension;

public class LocationsTestDimension extends HardcodedNiDimension {

	public LocationsTestDimension(String name, int depth) {
		super(name, depth);
	}

	public final static LocationsTestDimension instance = new LocationsTestDimension("locs", 4);

	@Override
	protected List<HNDNode> buildHardcodedElements() {
		return Arrays.asList(
		element(1, "Multi-country" ), 
		element(3, "United Arab Emirates" ), 
		element(4, "Afghanistan" ), 
		element(5, "Antigua and Barbuda" ), 
		element(6, "Anguilla" ), 
		element(7, "Albania" ), 
		element(8, "Armenia" ), 
		element(9, "Netherlands Antilles" ), 
		element(10, "Angola" ), 
		element(11, "Antarctica" ), 
		element(12, "Argentina" ), 
		element(13, "American Samoa" ), 
		element(14, "Austria" ), 
		element(15, "Australia" ), 
		element(16, "Aruba" ), 
		element(17, "Azerbaijan" ), 
		element(18, "Bosnia and Herzegovina" ), 
		element(19, "Barbados" ), 
		element(20, "Bangladesh" ), 
		element(21, "Belgium" ), 
		element(22, "Burkina Faso" ), 
		element(23, "Bulgaria" ), 
		element(24, "Bahrain" ), 
		element(26, "Benin" ), 
		element(27, "Bermuda" ), 
		element(28, "Brunei Darussalam" ), 
		element(29, "Bolivia" ), 
		element(30, "Brazil" ), 
		element(31, "Bahamas" ), 
		element(32, "Bhutan" ), 
		element(33, "Bouvet Island" ), 
		element(34, "Botswana" ), 
		element(35, "Belarus" ), 
		element(36, "Belize" ), 
		element(37, "Canada" ), 
		element(38, "Cocos (Keeling) Islands" ), 
		element(39, "Congo, Democratic Republic Of" ), 
		element(40, "Central African Republic" ), 
		element(41, "Congo" ), 
		element(42, "Switzerland" ), 
		element(43, "Cote d`Ivoire" ), 
		element(44, "Cook Islands" ), 
		element(45, "Chile" ), 
		element(46, "Cameroon" ), 
		element(47, "China" ), 
		element(48, "Colombia" ), 
		element(49, "Costa Rica" ), 
		element(50, "Cuba" ), 
		element(51, "Cape Verde" ), 
		element(52, "Christmas Island" ), 
		element(53, "Cyprus" ), 
		element(54, "Czech Republic" ), 
		element(55, "Germany" ), 
		element(56, "Djibouti" ), 
		element(57, "Denmark" ), 
		element(58, "Dominica" ), 
		element(59, "Dominican Republic" ), 
		element(60, "Algeria" ), 
		element(61, "Ecuador" ), 
		element(62, "Estonia" ), 
		element(63, "Egypt" ), 
		element(64, "Western Sahara" ), 
		element(65, "Eritrea" ), 
		element(66, "Spain" ), 
		element(68, "Finland" ), 
		element(69, "Fiji" ), 
		element(70, "Falkland Islands (Malvinas)" ), 
		element(71, "Micronesia" ), 
		element(72, "Faroe Islands" ), 
		element(73, "France" ), 
		element(74, "Gabon" ), 
		element(75, "United Kingdom" ), 
		element(76, "Grenada" ), 
		element(77, "Georgia" ), 
		element(78, "French Guiana" ), 
		element(79, "Ghana" ), 
		element(80, "Gibraltar" ), 
		element(81, "Greenland" ), 
		element(82, "Gambia" ), 
		element(83, "Guinea" ), 
		element(84, "Guadeloupe" ), 
		element(85, "Equatorial Guinea" ), 
		element(86, "Greece" ), 
		element(87, "S. Georgia and S. Sandwich Isls." ), 
		element(88, "Guatemala" ), 
		element(89, "Guam" ), 
		element(90, "Guinea-Bissau" ), 
		element(91, "Guyana" ), 
		element(92, "Hong Kong" ), 
		element(93, "Heard and McDonald Islands" ), 
		element(94, "Honduras" ), 
		element(95, "Croatia (Hrvatska)" ), 
		element(96, "Haiti" ), 
		element(97, "Hungary" ), 
		element(98, "Indonesia" ), 
		element(99, "Ireland" ), 
		element(100, "Israel" ), 
		element(101, "India" ), 
		element(102, "British Indian Ocean Territory" ), 
		element(103, "Iraq" ), 
		element(104, "Iran" ), 
		element(105, "Iceland" ), 
		element(106, "Italy" ), 
		element(107, "Jamaica" ), 
		element(108, "Jordan" ), 
		element(109, "Japan" ), 
		element(110, "Kenya" ), 
		element(111, "Kyrgyzstan" ), 
		element(112, "Cambodia" ), 
		element(113, "Kiribati" ), 
		element(114, "Comoros" ), 
		element(115, "Saint Kitts and Nevis" ), 
		element(116, "Korea (North)" ), 
		element(117, "Korea (South)" ), 
		element(118, "Kuwait" ), 
		element(119, "Cayman Islands" ), 
		element(120, "Kazakhstan" ), 
		element(121, "Laos" ), 
		element(122, "Lebanon" ), 
		element(123, "Saint Lucia" ), 
		element(124, "Liechtenstein" ), 
		element(125, "Sri Lanka" ), 
		element(1149, "Andorra" ), 
		element(126, "Liberia" ), 
		element(127, "Lesotho" ), 
		element(128, "Lithuania" ), 
		element(129, "Luxembourg" ), 
		element(130, "Latvia" ), 
		element(131, "Libya" ), 
		element(132, "Morocco" ), 
		element(133, "Monaco" ), 
		element(134, "Moldova" ), 
		element(135, "Madagascar" ), 
		element(136, "Marshall Islands" ), 
		element(137, "Macedonia" ), 
		element(138, "Mali" ), 
		element(139, "Myanmar" ), 
		element(140, "Mongolia" ), 
		element(141, "Macau" ), 
		element(142, "Northern Mariana Islands" ), 
		element(143, "Martinique" ), 
		element(144, "Mauritania" ), 
		element(145, "Montserrat" ), 
		element(146, "Malta" ), 
		element(147, "Mauritius" ), 
		element(148, "Maldives" ), 
		element(149, "Malawi" ), 
		element(150, "Mexico" ), 
		element(151, "Malaysia" ), 
		element(152, "Mozambique" ), 
		element(153, "Namibia" ), 
		element(154, "New Caledonia" ), 
		element(155, "Niger" ), 
		element(156, "Norfolk Island" ), 
		element(157, "Nigeria" ), 
		element(158, "Nicaragua" ), 
		element(159, "Netherlands" ), 
		element(160, "Norway" ), 
		element(161, "Nepal" ), 
		element(162, "Nauru" ), 
		element(163, "Niue" ), 
		element(164, "New Zealand (Aotearoa)" ), 
		element(165, "Oman" ), 
		element(166, "Panama" ), 
		element(167, "Peru" ), 
		element(168, "French Polynesia" ), 
		element(169, "Papua New Guinea" ), 
		element(170, "Philippines" ), 
		element(171, "Pakistan" ), 
		element(172, "Poland" ), 
		element(173, "St. Pierre and Miquelon" ), 
		element(174, "Pitcairn" ), 
		element(175, "Puerto Rico" ), 
		element(176, "Palestinian Territory, Occupied" ), 
		element(177, "Portugal" ), 
		element(178, "Palau" ), 
		element(179, "Paraguay" ), 
		element(180, "Qatar" ), 
		element(181, "Reunion" ), 
		element(182, "Romania" ), 
		element(183, "Russian Federation" ), 
		element(184, "Rwanda" ), 
		element(185, "Saudi Arabia" ), 
		element(186, "Solomon Islands" ), 
		element(187, "Seychelles" ), 
		element(188, "Sudan" ), 
		element(189, "Sweden" ), 
		element(190, "Singapore" ), 
		element(191, "St. Helena" ), 
		element(192, "Slovenia" ), 
		element(193, "Svalbard and Jan Mayen Islands" ), 
		element(194, "Slovak Republic" ), 
		element(195, "Sierra Leone" ), 
		element(196, "San Marino" ), 
		element(197, "Senegal" ), 
		element(198, "Somalia" ), 
		element(199, "Suriname" ), 
		element(200, "Sao Tome and Principe" ), 
		element(201, "El Salvador" ), 
		element(202, "Syria" ), 
		element(203, "Swaziland" ), 
		element(204, "Turks and Caicos Islands" ), 
		element(205, "Chad" ), 
		element(206, "French Southern Territories" ), 
		element(207, "Togo" ), 
		element(208, "Thailand" ), 
		element(209, "Tajikistan" ), 
		element(210, "Tokelau" ), 
		element(211, "Turkmenistan" ), 
		element(212, "Tunisia" ), 
		element(213, "Tonga" ), 
		element(214, "East Timor" ), 
		element(215, "Turkey" ), 
		element(216, "Trinidad and Tobago" ), 
		element(217, "Tuvalu" ), 
		element(218, "Taiwan" ), 
		element(219, "Tanzania Mainland", 
			element(1158, "Simiyu Region", 
				element(1214, "Bariadi TC" ), 
				element(1215, "Busega DC" ), 
				element(1216, "Itilima DC" ), 
				element(1217, "Ras Simiyu" ), 
				element(1239, "Bariadi DC" ), 
				element(1240, "Maswa DC" ), 
				element(1241, "Meatu DC" ) ), 
			element(1163, "Njombe Region", 
				element(1164, "Njombe RAS" ), 
				element(1166, "Makete DC" ), 
				element(1167, "Ludewa DC" ), 
				element(1168, "Njombe DC" ), 
				element(1203, "Njombe TC" ), 
				element(1204, "Makambako TC" ), 
				element(1205, "Wanging'ombe DC" ) ), 
			element(1170, "Geita", 
				element(1171, "Chato DC" ), 
				element(1233, "Geita TC" ), 
				element(1234, "Geita DC" ), 
				element(1235, "Bukombe DC" ), 
				element(1236, "Mbogwe" ), 
				element(1237, "Nyangh'wale" ), 
				element(1238, "Ras Geita" ) ), 
			element(1227, "Katavi", 
				element(1228, "Mpanda DC" ), 
				element(1229, "Mlele DC" ), 
				element(1230, "Mpanda TC" ), 
				element(1231, "Nsimbo DC" ), 
				element(1232, "Ras Katavi" ) ), 
			element(258, "Arusha", 
				element(1110, "Meru DC" ), 
				element(1111, "Arusha DC" ), 
				element(1125, "Longido DC" ), 
				element(1173, "Arusha CC" ), 
				element(1174, "Ras Arusha" ), 
				element(388, "Arusha MC" ), 
				element(389, "Arumeru DC" ), 
				element(390, "Monduli DC" ), 
				element(391, "Ngorongoro DC" ), 
				element(392, "Karatu DC" ) ), 
			element(259, "Coast", 
				element(1175, "Ras Coast" ), 
				element(393, "Kibaha TC" ), 
				element(394, "Bagamoyo DC" ), 
				element(395, "Mafia DC" ), 
				element(396, "Kisarawe DC" ), 
				element(397, "Kibaha DC" ), 
				element(398, "Rufiji DC" ), 
				element(399, "Mkuranga DC" ) ), 
			element(260, "Dar-es-Salaam", 
				element(1176, "Ras Dar es Salaam" ), 
				element(400, "Ilala MC" ), 
				element(401, "Kinondoni MC" ), 
				element(402, "Temeke MC" ), 
				element(403, "Dar es Salaam CC" ) ), 
			element(261, "Dodoma", 
				element(1112, "Bahi DC" ), 
				element(1113, "Chamwino DC" ), 
				element(1177, "Ras Dodoma" ), 
				element(1178, "Chemba DC" ), 
				element(404, "Dodoma MC" ), 
				element(405, "Dodoma DC" ), 
				element(406, "Kondoa DC" ), 
				element(407, "Mpwapwa DC" ), 
				element(408, "Kongwa DC" ) ), 
			element(262, "Iringa", 
				element(1150, "Iringa RAS" ), 
				element(409, "Iringa MC" ), 
				element(410, "Iringa DC" ), 
				element(411, "Mufindi DC" ), 
				element(415, "Kilolo DC" ) ), 
			element(263, "Kagera", 
				element(1116, "Missenyi DC" ), 
				element(1179, "Kyerwa DC" ), 
				element(1180, "Ras Kagera" ), 
				element(416, "Bukoba MC" ), 
				element(427, "Karagwe DC" ), 
				element(428, "Biharamulo DC" ), 
				element(429, "Muleba DC" ), 
				element(430, "Bukoba DC" ), 
				element(431, "Ngara DC" ) ), 
			element(264, "Kigoma", 
				element(1181, "Kasulu TC" ), 
				element(1182, "Kakonko DC" ), 
				element(1183, "Uvinza DC" ), 
				element(1184, "Buhigwe DC" ), 
				element(1185, "Ras Kigoma" ), 
				element(432, "Kigoma MC" ), 
				element(433, "Kigoma DC" ), 
				element(434, "Kasulu DC" ), 
				element(435, "Kibondo DC" ) ), 
			element(265, "Kilimanjaro", 
				element(1126, "Siha DC" ), 
				element(1186, "Ras Kilimanjaro" ), 
				element(436, "Moshi MC" ), 
				element(437, "Hai DC" ), 
				element(438, "Moshi DC" ), 
				element(439, "Rombo DC" ), 
				element(440, "Same DC" ), 
				element(441, "Mwanga DC" ) ), 
			element(266, "Lindi", 
				element(1187, "Ras Lindi" ), 
				element(442, "Lindi TC" ), 
				element(443, "Nachingwea DC" ), 
				element(444, "Kilwa DC" ), 
				element(445, "Liwale DC" ), 
				element(446, "Lindi MC" ), 
				element(447, "Ruangwa DC" ) ), 
			element(267, "Manyara", 
				element(1188, "Ras Manyara" ), 
				element(448, "Babati TC" ), 
				element(449, "Babati DC" ), 
				element(450, "Hanang DC" ), 
				element(451, "Kiteto DC" ), 
				element(452, "Mbulu DC" ), 
				element(453, "Simanjiro DC" ) ), 
			element(268, "Mara", 
				element(1127, "Rorya DC" ), 
				element(1189, "Tarime TC" ), 
				element(1190, "Butiama DC" ), 
				element(1191, "Ras Mara" ), 
				element(454, "Musoma MC" ), 
				element(455, "Bunda DC" ), 
				element(456, "Musoma DC" ), 
				element(457, "Serengeti DC" ), 
				element(458, "Tarime DC" ) ), 
			element(269, "Mbeya", 
				element(1192, "Busokelo DC" ), 
				element(1193, "Momba DC" ), 
				element(1194, "Tunduma DC" ), 
				element(1195, "Ras Mbeya" ), 
				element(459, "Mbeya CC" ), 
				element(460, "Chunya DC" ), 
				element(461, "Ileje DC" ), 
				element(462, "Kyela DC" ), 
				element(463, "Mbeya DC" ), 
				element(464, "Mbozi DC" ), 
				element(465, "Rungwe DC" ), 
				element(466, "Mbarali DC" ) ), 
			element(270, "Morogoro", 
				element(1197, "Gairo DC" ), 
				element(1198, "Ras Morogoro" ), 
				element(467, "Morogoro MC" ), 
				element(468, "Morogoro DC" ), 
				element(469, "Kilosa DC" ), 
				element(470, "Kilombero DC" ), 
				element(471, "Ulanga DC" ), 
				element(472, "Mvomero DC" ) ), 
			element(271, "Mtwara", 
				element(1128, "Nanyumbu DC" ), 
				element(1199, "Masasi TC" ), 
				element(1200, "Ras Mtwara" ), 
				element(473, "Mtwara MC" ), 
				element(474, "Mtwara DC" ), 
				element(475, "Newala DC" ), 
				element(476, "Masasi DC" ), 
				element(477, "Tandahimba DC" ) ), 
			element(272, "Mwanza", 
				element(1201, "Ilemela DC" ), 
				element(1202, "Ras Mwanza" ), 
				element(478, "Mwanza CC" ), 
				element(479, "Ukerewe DC" ), 
				element(480, "Sengerema DC" ), 
				element(482, "Kwimba DC" ), 
				element(483, "Magu DC" ), 
				element(484, "Misungwi DC" ) ), 
			element(273, "Rukwa", 
				element(1206, "Kalambo DC" ), 
				element(1207, "Ras Rukwa" ), 
				element(485, "Sumbawanga DC" ), 
				element(486, "Sumbawanga MC" ), 
				element(488, "Nkansi DC" ) ), 
			element(274, "Ruvuma", 
				element(1208, "Nyasa DC" ), 
				element(1209, "Ras Ruvuma" ), 
				element(489, "Songea MC" ), 
				element(490, "Songea DC" ), 
				element(491, "Tunduru DC" ), 
				element(492, "Mbinga DC" ), 
				element(493, "Namtumbo DC" ) ), 
			element(275, "Shinyanga", 
				element(1210, "Usheta DC" ), 
				element(1211, "Kahama TC" ), 
				element(1212, "Msalala DC" ), 
				element(1213, "Ras Shinyanga" ), 
				element(494, "Shinyanga MC" ), 
				element(495, "Shinyanga DC" ), 
				element(498, "Kahama DC" ), 
				element(501, "Kishapu DC" ) ), 
			element(276, "Singida", 
				element(1218, "Ikungi DC" ), 
				element(1219, "Mkalama DC" ), 
				element(1220, "Ras Singida" ), 
				element(502, "Singida MC" ), 
				element(503, "Singida DC" ), 
				element(504, "Iramba DC" ), 
				element(505, "Manyoni DC" ) ), 
			element(277, "Tabora", 
				element(1221, "Kaliua DC" ), 
				element(1222, "Nzega TC" ), 
				element(1223, "Ras Tabora" ), 
				element(506, "Tabora MC" ), 
				element(507, "Igunga DC" ), 
				element(508, "Nzega DC" ), 
				element(509, "Tabora DC" ), 
				element(510, "Urambo DC" ), 
				element(511, "Sikonge DC" ) ), 
			element(278, "Tanga", 
				element(1130, "Mkinga DC" ), 
				element(1224, "Handeni TC" ), 
				element(1225, "Bumbuli DC" ), 
				element(1226, "Ras Tanga" ), 
				element(512, "Tanga CC" ), 
				element(513, "Korogwe TC" ), 
				element(514, "Muheza DC" ), 
				element(515, "Pangani DC" ), 
				element(516, "Korogwe DC" ), 
				element(517, "Handeni DC" ), 
				element(518, "Lushoto DC" ), 
				element(519, "Kilindi DC" ) ) ), 
		element(1243, "Zanzibar", 
			element(1249, "North Pemba", 
				element(1263, "Micheweni District" ), 
				element(1264, "Wete District" ) ), 
			element(1250, "North Unguja", 
				element(1261, "North A District" ), 
				element(1262, "North B District" ) ), 
			element(1251, "South District", 
				element(1258, "Central District" ), 
				element(1260, "South Uguja" ) ), 
			element(1252, "South Pemba", 
				element(1256, "Chakechake" ), 
				element(1257, "Mkoani" ) ), 
			element(1253, "Urban West", 
				element(1254, "Urban District" ), 
				element(1255, "West District" ) ) ), 
		element(220, "Ukraine" ), 
		element(1244, "Burundi" ), 
		element(221, "Uganda" ), 
		element(1245, "Ethiopia" ), 
		element(222, "US Minor Outlying Islands" ), 
		element(1246, "Country A" ), 
		element(223, "United States" ), 
		element(224, "Uruguay" ), 
		element(225, "Uzbekistan" ), 
		element(226, "Vatican City State (Holy See)" ), 
		element(227, "Saint Vincent and the Grenadines" ), 
		element(228, "Venezuela" ), 
		element(229, "Virgin Islands (British)" ), 
		element(230, "Virgin Islands (U.S.)" ), 
		element(231, "Viet Nam" ), 
		element(232, "Vanuatu" ), 
		element(233, "Wallis and Futuna Islands" ), 
		element(234, "Samoa" ), 
		element(235, "Yemen" ), 
		element(236, "Mayotte" ), 
		element(237, "Yugoslavia" ), 
		element(238, "South Africa" ), 
		element(239, "Zambia" ), 
		element(240, "Zimbabwe" ));
	}
}
