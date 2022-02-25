INSERT INTO `categorie` (`id`, `nom`) VALUES
(1, 'categorie1'),
(2, 'categorie2'),
(3, 'categorie3'),
(4, 'categorie4'),
(5, 'categorie5'),
(6, 'categorie6'),
(7, 'categorie7'),
(8, 'categorie8');

INSERT INTO `vehicule` (`id`, `immatriculation`, `marque`, `modele`, `nb_places`, `statut`, `photo`, `id_categorie`) VALUES
(1, 'PK-156-ML', 'Toyota', 'Yaris', 5, 'En service', 'https://img-31.ccm2.net/oUzoumT47RAmihtpA_Gbxo4mAYA=/1240x/smart/dc46cec6193b4acd8570ebfa77adc2ce/ccmcms-hugo/10557729.gif', 1),
(2, 'PK-157-ML', 'Peugeot', '207', 5, 'En réparation', 'https://img-31.ccm2.net/oUzoumT47RAmihtpA_Gbxo4mAYA=/1240x/smart/dc46cec6193b4acd8570ebfa77adc2ce/ccmcms-hugo/10557729.gif', 2),
(3, 'PK-158-ML', 'Citroen', 'C3', 5, 'En service', 'https://img-31.ccm2.net/oUzoumT47RAmihtpA_Gbxo4mAYA=/1240x/smart/dc46cec6193b4acd8570ebfa77adc2ce/ccmcms-hugo/10557729.gif', 3);