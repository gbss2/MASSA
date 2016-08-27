package massa.enrichment;

import java.util.HashMap;
import java.util.Map;

public class BioEnrichment2 implements java.io.Serializable {

	public Map<String, String> pgkbPathwayCount;
	public Map<String, String> pgkbDiseaseCount;
	public Map<String, String> pgkbDrugCount;

	public BioEnrichment2() {

		pgkbPathwayCount = new HashMap<String, String>();
		pgkbDiseaseCount = new HashMap<String, String>();
		pgkbDrugCount = new HashMap<String, String>();

	}

	public void setpgkbPathwayCount() {

		pgkbPathwayCount.put("COUNT", "73707");
		pgkbPathwayCount.put("null", "26810");
		pgkbPathwayCount.put("(1", "7");
		pgkbPathwayCount.put("1", "2");
		pgkbPathwayCount.put("2-LTR circle formation - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("3' -UTR-mediated translational regulation - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("5-Phosphoribose 1-diphosphate biosynthesis - (Reactome via Pathway Interaction Database)",
				"5");
		pgkbPathwayCount.put("A third proteolytic cleavage releases NICD - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put("a4b1 and a4b7 Integrin signaling - (Pathway Interaction Database NCI-Nature Curated)",
				"1");
		pgkbPathwayCount.put("a6b1 and a6b4 Integrin signaling - (Pathway Interaction Database NCI-Nature Curated)",
				"76");
		pgkbPathwayCount.put("ABC-family proteins mediated transport - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount
				.put("ABH2 mediated Reversal of Alkylation Damage - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount
				.put("ABH3 mediated Reversal of Alkylation Damage - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put(
				"Abortive elongation of HIV-1 transcript in the absence of Tat - (Reactome via Pathway Interaction Database)",
				"76");
		pgkbPathwayCount.put("ACE Inhibitor Pathway", "39");
		pgkbPathwayCount.put("Acetaldehyde is oxidized by NAD+ to form acetate", "8");
		pgkbPathwayCount.put("Acetaminophen Pathway", "76");
		pgkbPathwayCount.put("Acetate", "5");
		pgkbPathwayCount.put("Acetylation - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put(
				"acetylation and deacetylation of rela in nucleus - (BioCarta via Pathway Interaction Database)", "15");
		pgkbPathwayCount.put(
				"Acetylcholine Neurotransmitter Release Cycle - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("actions of nitric oxide in the heart - (BioCarta via Pathway Interaction Database)",
				"88");
		pgkbPathwayCount.put(
				"Activated AMPK stimulates fatty-acid oxidation in muscle - (Reactome via Pathway Interaction Database)",
				"21");
		pgkbPathwayCount.put("Activated TLR4 signalling - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Activation", "30");
		pgkbPathwayCount.put(
				"Activation and oligomerization of BAK protein - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put(
				"Activation of APC/C and APC/C:Cdc20 mediated degradation of mitotic proteins - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount.put(
				"Activation of ATR in response to replication stress - (Reactome via Pathway Interaction Database)",
				"50");
		pgkbPathwayCount.put(
				"Activation of BAD and translocation to mitochondria - (Reactome via Pathway Interaction Database)",
				"18");
		pgkbPathwayCount.put("Activation of BH3-only proteins - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Activation of BIM and translocation to mitochondria - (Reactome via Pathway Interaction Database)",
				"8");
		pgkbPathwayCount.put(
				"Activation of BMF and translocation to mitochondria - (Reactome via Pathway Interaction Database)",
				"11");
		pgkbPathwayCount.put("Activation of C3 and C5 - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put(
				"activation of camp-dependent protein kinase pka - (BioCarta via Pathway Interaction Database)", "50");
		pgkbPathwayCount.put(
				"Activation of caspases through apoptosome-mediated cleavage - (Reactome via Pathway Interaction Database)",
				"8");
		pgkbPathwayCount.put(
				"activation of csk by camp-dependent protein kinase inhibits signaling through the t cell receptor - (BioCarta via Pathway Interaction Database)",
				"108");
		pgkbPathwayCount.put("Activation of DNA fragmentation factor - (Reactome via Pathway Interaction Database)",
				"11");
		pgkbPathwayCount.put(
				"Activation of NOXA and translocation to mitochondria - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Activation of PKB - (Reactome via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put(
				"activation of pkc through g-protein coupled receptors - (BioCarta via Pathway Interaction Database)",
				"19");
		pgkbPathwayCount.put("Activation of Pro-Caspase 8 - (Reactome via Pathway Interaction Database)", "26");
		pgkbPathwayCount.put(
				"Activation of PUMA and translocation to mitochondria - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put("Activation of the mRNA upon binding of the cap-binding complex and eIFs", "11");
		pgkbPathwayCount.put("Activation of the pre-replicative complex - (Reactome via Pathway Interaction Database)",
				"49");
		pgkbPathwayCount.put("Activation of TRKA receptors - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Adenylate cyclase activating pathway - (Reactome via Pathway Interaction Database)",
				"12");
		pgkbPathwayCount.put("Adenylate cyclase inhibitory pathway - (Reactome via Pathway Interaction Database)",
				"15");
		pgkbPathwayCount.put("adp-ribosylation factor - (BioCarta via Pathway Interaction Database)", "31");
		pgkbPathwayCount.put("Adrenoceptors - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Agents Acting on the Renin-Angiotensin System Pathway", "40");
		pgkbPathwayCount.put("Agmatine biosynthesis - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("agrin in postsynaptic differentiation - (BioCarta via Pathway Interaction Database)",
				"73");
		pgkbPathwayCount.put("ahr signal transduction pathway - (BioCarta via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put(
				"akap95 role in mitosis and chromosome dynamics - (BioCarta via Pathway Interaction Database)", "52");
		pgkbPathwayCount.put("AKT phosphorylates targets in the cytosol - (Reactome via Pathway Interaction Database)",
				"20");
		pgkbPathwayCount.put("AKT phosphorylates targets in the nucleus - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put("akt signaling pathway - (BioCarta via Pathway Interaction Database)", "58");
		pgkbPathwayCount.put("AKT-mediated inactivation of FOXO1A - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("Alanine metabolism - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("Alcohol Dehydrogenase - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Aldehyde Dehydrogenase - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("alk in cardiac myocytes - (BioCarta via Pathway Interaction Database)", "45");
		pgkbPathwayCount.put("Alpha-oxidation of phytanate - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put(
				"alpha-synuclein and parkin-mediated proteolysis in parkinson`s disease - (BioCarta via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put("Alpha-synuclein signaling - (Pathway Interaction Database NCI-Nature Curated)", "52");
		pgkbPathwayCount.put("Alternative complement activation - (Reactome via Pathway Interaction Database)", "15");
		pgkbPathwayCount.put("alternative complement pathway - (BioCarta via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("Alternative NF-kappaB pathway - (Pathway Interaction Database NCI-Nature Curated)", "29");
		pgkbPathwayCount.put("amb2 Integrin signaling - (Pathway Interaction Database NCI-Nature Curated)", "81");
		pgkbPathwayCount.put("Amine ligand-binding receptors - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Amine Oxidase reactions - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Amine-derived hormones - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Amino Acid conjugation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Amino acid transport across the plasma membrane - (Reactome via Pathway Interaction Database)", "76");
		pgkbPathwayCount.put(
				"Amino acid uptake across the plasma membrane - (Reactome via Pathway Interaction Database)", "53");
		pgkbPathwayCount.put("Amodiaquine Pathway", "14");
		pgkbPathwayCount.put(
				"AMPK inhibits chREBP transcriptional activation activity - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put(
				"Amplification  of signal from unattached  kinetochores via a MAD2  inhibitory signal - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put(
				"Amplification of signal from the kinetochores - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Androgen biosynthesis - (Reactome via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put("Androgen-mediated signaling - (Pathway Interaction Database NCI-Nature Curated)", "1");
		pgkbPathwayCount.put(
				"Angiopoietin receptor Tie2-mediated signaling - (Pathway Interaction Database NCI-Nature Curated)",
				"164");
		pgkbPathwayCount.put(
				"angiotensin ii mediated activation of jnk pathway via pyk2 dependent signaling - (BioCarta via Pathway Interaction Database)",
				"90");
		pgkbPathwayCount.put("anthrax toxin mechanism of action - (BioCarta via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Anti-diabetic Drug Nateglinide Pathway", "5");
		pgkbPathwayCount.put("Anti-diabetic Drug Potassium Channel Inhibitors Pathway", "83");
		pgkbPathwayCount.put("Anti-diabetic Drug Repaglinide Pathway", "5");
		pgkbPathwayCount.put("Antiarrhythmic Pathway", "127");
		pgkbPathwayCount.put("antigen processing and presentation - (BioCarta via Pathway Interaction Database)", "61");
		pgkbPathwayCount.put("Antimetabolite Pathway - Folate Cycle", "50");
		pgkbPathwayCount.put("antisense pathway - (BioCarta via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("APC-Cdc20 mediated degradation of Nek2A - (Reactome via Pathway Interaction Database)",
				"39");
		pgkbPathwayCount.put(
				"APC/C-mediated degradation of cell cycle proteins - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put(
				"APC/C:Cdc20 mediated degradation of Cyclin B - (Reactome via Pathway Interaction Database)", "39");
		pgkbPathwayCount.put(
				"APC/C:Cdc20 mediated degradation of mitotic proteins - (Reactome via Pathway Interaction Database)",
				"29");
		pgkbPathwayCount.put(
				"APC/C:Cdc20 mediated degradation of Securin - (Reactome via Pathway Interaction Database)", "101");
		pgkbPathwayCount.put(
				"APC/C:Cdh1 mediated degradation of Cdc20 and other APC/C:Cdh1 targeted proteins in late mitosis/early G1 - (Reactome via Pathway Interaction Database)",
				"103");
		pgkbPathwayCount.put(
				"APOBEC3G mediated resistance to HIV-1 infection - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("Apoptosis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Apoptosis induced DNA fragmentation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Apoptotic cleavage of cell adhesion  proteins - (Reactome via Pathway Interaction Database)", "17");
		pgkbPathwayCount.put("Apoptotic cleavage of cellular proteins - (Reactome via Pathway Interaction Database)",
				"46");
		pgkbPathwayCount.put(
				"apoptotic dna-fragmentation and tissue homeostasis - (BioCarta via Pathway Interaction Database)",
				"23");
		pgkbPathwayCount.put("Apoptotic execution  phase - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Apoptotic factor-mediated response - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"apoptotic signaling in response to dna damage - (BioCarta via Pathway Interaction Database)", "32");
		pgkbPathwayCount.put("Arf1 pathway - (Pathway Interaction Database NCI-Nature Curated)", "47");
		pgkbPathwayCount.put("Arf6 downstream pathway - (Pathway Interaction Database NCI-Nature Curated)", "31");
		pgkbPathwayCount.put(
				"Arf6 mediated densensitization of LHCGR - (Pathway Interaction Database NCI-Nature Curated)", "9");
		pgkbPathwayCount.put("Arf6 signaling events - (Pathway Interaction Database NCI-Nature Curated)", "77");
		pgkbPathwayCount.put("Arf6 trafficking events - (Pathway Interaction Database NCI-Nature Curated)", "65");
		pgkbPathwayCount.put("ARMS-mediated activation - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("Aromatase Inhibitor Pathway (Breast Cell)", "16");
		pgkbPathwayCount.put("Aromatase Inhibitor Pathway (Multiple Tissues)", "6");
		pgkbPathwayCount.put(
				"Aromatic amines can be N-hydroxylated or N-dealkylated by CYP1A2 - (Reactome via Pathway Interaction Database)",
				"12");
		pgkbPathwayCount.put("Artemisinin and Derivatives Pathway", "17");
		pgkbPathwayCount.put("Aspartate", "60");
		pgkbPathwayCount.put(
				"aspirin blocks signaling pathway involved in platelet activation - (BioCarta via Pathway Interaction Database)",
				"51");
		pgkbPathwayCount.put(
				"Assembly of the ORC complex at the origin of replication - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put("Assembly of the pre-replicative complex - (Reactome via Pathway Interaction Database)",
				"24");
		pgkbPathwayCount.put(
				"Assembly of the RAD50-MRE11-NBS1 complex at DNA double-strand breaks - (Reactome via Pathway Interaction Database)",
				"8");
		pgkbPathwayCount.put(
				"Assembly of the RAD51-ssDNA nucleoprotein complex - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put(
				"Assembly of Viral Components at the Budding Site - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Association of licensing factors with the pre-replicative complex - (Reactome via Pathway Interaction Database)",
				"30");
		pgkbPathwayCount.put(
				"Association of TriC/CCT with target proteins during biosynthesis - (Reactome via Pathway Interaction Database)",
				"16");
		pgkbPathwayCount.put(
				"Astrocytic Glutamate-Glutamine Uptake And Metabolism - (Reactome via Pathway Interaction Database)",
				"20");
		pgkbPathwayCount.put(
				"ATM mediated phosphorylation of repair proteins - (Reactome via Pathway Interaction Database)", "15");
		pgkbPathwayCount.put(
				"ATM mediated response to DNA double-strand break - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("atm signaling pathway - (BioCarta via Pathway Interaction Database)", "39");
		pgkbPathwayCount.put("Atorvastatin/Lovastatin/Simvastatin Pathway", "34");
		pgkbPathwayCount.put("ATP formation - (Reactome via Pathway Interaction Database)", "14");
		pgkbPathwayCount.put("Attachment of GPI anchor to uPAR - (Reactome via Pathway Interaction Database)", "24");
		pgkbPathwayCount.put("attenuation of gpcr signaling - (BioCarta via Pathway Interaction Database)", "45");
		pgkbPathwayCount.put("Atypical NF-kappaB pathway - (Pathway Interaction Database NCI-Nature Curated)", "30");
		pgkbPathwayCount.put("Aurora A signaling - (Pathway Interaction Database NCI-Nature Curated)", "91");
		pgkbPathwayCount.put("Aurora B signaling - (Pathway Interaction Database NCI-Nature Curated)", "92");
		pgkbPathwayCount.put("Aurora C signaling - (Pathway Interaction Database NCI-Nature Curated)", "10");
		pgkbPathwayCount.put("Autodegradation of Cdh1 by Cdh1:APC/C - (Reactome via Pathway Interaction Database)",
				"102");
		pgkbPathwayCount
				.put("Autointegration results in viral DNA circles - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Axonal growth inhibition (RHOA activation) - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put("Axonal growth stimulation - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("b cell survival pathway - (BioCarta via Pathway Interaction Database)", "51");
		pgkbPathwayCount.put("BARD1 signaling events - (Pathway Interaction Database NCI-Nature Curated)", "171");
		pgkbPathwayCount.put("Base Excision Repair - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Base-Excision Repair", "1");
		pgkbPathwayCount.put(
				"Base-free sugar-phosphate removal via the single-nucleotide replacement pathway - (Reactome via Pathway Interaction Database)",
				"5");
		pgkbPathwayCount.put(
				"basic mechanism of action of ppara pparb(d) and pparg and effects on gene expression - (BioCarta via Pathway Interaction Database)",
				"17");
		pgkbPathwayCount.put("basic mechanisms of sumoylation - (BioCarta via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put("Basigin interactions - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("bcr signaling pathway - (BioCarta via Pathway Interaction Database)", "88");
		pgkbPathwayCount.put("BCR signaling pathway - (Pathway Interaction Database NCI-Nature Curated)", "154");
		pgkbPathwayCount.put("Benzodiazepine Pathway", "50");
		pgkbPathwayCount
				.put("Beta oxidation of butanoyl-CoA to acetyl-CoA - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put(
				"Beta oxidation of decanoyl-CoA to octanoyl-CoA-CoA - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put(
				"Beta oxidation of hexanoyl-CoA to butanoyl-CoA - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put(
				"Beta oxidation of lauroyl-CoA to decanoyl-CoA-CoA - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put(
				"Beta oxidation of myristoyl-CoA to lauroyl-CoA - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put(
				"Beta oxidation of octanoyl-CoA to hexanoyl-CoA - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put(
				"Beta oxidation of palmitoyl-CoA to myristoyl-CoA - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Beta-agonist/Beta-blocker Pathway", "233");
		pgkbPathwayCount.put("Beta-catenin phosphorylation cascade - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Beta-oxidation of pristanoyl-CoA - (Reactome via Pathway Interaction Database)", "17");
		pgkbPathwayCount.put(
				"Beta-oxidation of very long chain fatty acids - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put(
				"BH3-only proteins associate with and inactivate anti-apoptotic BCL-2 members - (Reactome via Pathway Interaction Database)",
				"17");
		pgkbPathwayCount.put("Binding and entry of HIV virion - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put(
				"bioactive peptide induced signaling pathway - (BioCarta via Pathway Interaction Database)", "100");
		pgkbPathwayCount.put("Biological oxidations - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Biotin metabolism - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("Bisphosphonate Pathway", "53");
		pgkbPathwayCount.put("BMP receptor signaling - (Pathway Interaction Database NCI-Nature Curated)", "56");
		pgkbPathwayCount.put("bone remodeling - (BioCarta via Pathway Interaction Database)", "39");
		pgkbPathwayCount.put("BoNT Light Chain Types A", "6");
		pgkbPathwayCount.put("BoNT Light Chain Types B", "14");
		pgkbPathwayCount.put("Botulinum neurotoxicity - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Branched-chain amino acid catabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("brca1 dependent ub ligase activity - (BioCarta via Pathway Interaction Database)", "104");
		pgkbPathwayCount.put(
				"Breakdown of hydrogen peroxide to water and molecular oxygen - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Breakdown of the nuclear lamina - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put(
				"btg family proteins and cell cycle regulation - (BioCarta via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put("Budding - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Busulfan Pathway", "109");
		pgkbPathwayCount.put(
				"c-src mediated regulation of Cx43 function and closure of gap junctions - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put("C6 deamination of adenosine - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put(
				"ca-calmodulin-dependent protein kinase activation - (BioCarta via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("Ca-dependent events - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"cadmium induces dna synthesis and proliferation in macrophages - (BioCarta via Pathway Interaction Database)",
				"52");
		pgkbPathwayCount.put("Caffeine Pathway", "29");
		pgkbPathwayCount.put(
				"Calcineurin-regulated NFAT-dependent transcription in lymphocytes - (Pathway Interaction Database NCI-Nature Curated)",
				"114");
		pgkbPathwayCount.put(
				"calcium signaling by hbx of hepatitis b virus - (BioCarta via Pathway Interaction Database)", "45");
		pgkbPathwayCount.put(
				"Calcium signaling in the CD4+ TCR pathway - (Pathway Interaction Database NCI-Nature Curated)", "65");
		pgkbPathwayCount.put("Calmodulin induced events - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("CaM pathway - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("Cam-PDE 1 activation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("CaMK IV-mediated phosphorylation of CREB - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put("Canonical NF-kappaB pathway - (Pathway Interaction Database NCI-Nature Curated)", "49");
		pgkbPathwayCount.put("Canonical Wnt signaling pathway - (Pathway Interaction Database NCI-Nature Curated)",
				"75");
		pgkbPathwayCount.put("Cap-dependent Translation Initiation - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Carbamazepine Pathway", "38");
		pgkbPathwayCount.put("cardiac protection against ros - (BioCarta via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put(
				"carm1 and regulation of the estrogen receptor - (BioCarta via Pathway Interaction Database)", "35");
		pgkbPathwayCount.put("Carnitine synthesis - (Reactome via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put("caspase cascade in apoptosis - (BioCarta via Pathway Interaction Database)", "60");
		pgkbPathwayCount.put("Caspase cascade in apoptosis - (Pathway Interaction Database NCI-Nature Curated)", "97");
		pgkbPathwayCount.put("Caspase-8 is formed from procaspase-8 - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put(
				"Caspase-mediated cleavage of cytoskeletal proteins - (Reactome via Pathway Interaction Database)",
				"38");
		pgkbPathwayCount.put("Catecholamines biosynthesis - (Reactome via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put(
				"cbl mediated ligand-induced downregulation of egf receptors pathway - (BioCarta via Pathway Interaction Database)",
				"15");
		pgkbPathwayCount.put("ccr3 signaling in eosinophils - (BioCarta via Pathway Interaction Database)", "73");
		pgkbPathwayCount.put("cd40l signaling pathway - (BioCarta via Pathway Interaction Database)", "24");
		pgkbPathwayCount.put(
				"Cdc20:Phospho-APC/C mediated degradation of Cyclin A - (Reactome via Pathway Interaction Database)",
				"107");
		pgkbPathwayCount.put(
				"cdc25 and chk1 regulatory pathway in response to dna damage - (BioCarta via Pathway Interaction Database)",
				"22");
		pgkbPathwayCount.put(
				"CDC6 association with the ORC:origin complex - (Reactome via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put("cdk regulation of dna replication - (BioCarta via Pathway Interaction Database)", "34");
		pgkbPathwayCount.put(
				"CDK-mediated phosphorylation and removal of Cdc6 - (Reactome via Pathway Interaction Database)", "79");
		pgkbPathwayCount.put(
				"CDT1 association with the CDC6:ORC:origin complex - (Reactome via Pathway Interaction Database)",
				"98");
		pgkbPathwayCount.put("Celecoxib Pathway", "1238");
		pgkbPathwayCount.put("Cell Cycle", "1");
		pgkbPathwayCount.put("Cell Cycle Checkpoints - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("cell cycle: g1/s check point - (BioCarta via Pathway Interaction Database)", "62");
		pgkbPathwayCount.put("cell cycle: g2/m checkpoint - (BioCarta via Pathway Interaction Database)", "49");
		pgkbPathwayCount.put("Cell death signalling via NRAGE", "1");
		pgkbPathwayCount.put(
				"Cell surface interactions at the vascular wall - (Reactome via Pathway Interaction Database)", "92");
		pgkbPathwayCount.put("cell to cell adhesion signaling - (BioCarta via Pathway Interaction Database)", "20");
		pgkbPathwayCount.put("Cellular roles of Anthrax toxin - (Pathway Interaction Database NCI-Nature Curated)",
				"34");
		pgkbPathwayCount.put("Centrosome maturation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("ceramide signaling pathway - (BioCarta via Pathway Interaction Database)", "63");
		pgkbPathwayCount.put("Ceramide signaling pathway - (Pathway Interaction Database NCI-Nature Curated)", "90");
		pgkbPathwayCount.put("Ceramide signalling - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put(
				"chaperones modulate interferon signaling pathway - (BioCarta via Pathway Interaction Database)", "32");
		pgkbPathwayCount.put("Chaperonin-mediated protein folding - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Chemokine receptors bind chemokines - (Reactome via Pathway Interaction Database)", "30");
		pgkbPathwayCount.put(
				"Chk1/Chk2(Cds1) mediated inactivation of Cyclin B:Cdk1 complex - (Reactome via Pathway Interaction Database)",
				"16");
		pgkbPathwayCount.put("Cholesterol biosynthesis - (Reactome via Pathway Interaction Database)", "29");
		pgkbPathwayCount.put("ChREBP activates metabolic gene expression - (Reactome via Pathway Interaction Database)",
				"8");
		pgkbPathwayCount
				.put("chrebp regulation by carbohydrates and camp - (BioCarta via Pathway Interaction Database)", "66");
		pgkbPathwayCount.put(
				"chromatin remodeling by hswi/snf atp-dependent complexes - (BioCarta via Pathway Interaction Database)",
				"51");
		pgkbPathwayCount.put("Chylomicron-mediated lipid transport - (Reactome via Pathway Interaction Database)",
				"45");
		pgkbPathwayCount.put("Circadian rhythm pathway - (Pathway Interaction Database NCI-Nature Curated)", "33");
		pgkbPathwayCount.put("Citalopram Pathway", "21");
		pgkbPathwayCount.put("Citric acid cycle (TCA cycle) - (Reactome via Pathway Interaction Database)", "40");
		pgkbPathwayCount.put("Class A/1 (Rhodopsin-like receptors) - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Class I PI3K signaling events - (Pathway Interaction Database NCI-Nature Curated)", "57");
		pgkbPathwayCount.put(
				"Class I PI3K signaling events mediated by Akt - (Pathway Interaction Database NCI-Nature Curated)",
				"81");
		pgkbPathwayCount
				.put("Class IB PI3K non-lipid kinase events - (Pathway Interaction Database NCI-Nature Curated)", "9");
		pgkbPathwayCount.put(
				"Classical antibody-mediated complement activation - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put("classical complement pathway - (BioCarta via Pathway Interaction Database)", "27");
		pgkbPathwayCount.put(
				"Cleavage of Growing Transcript in the Termination Region - (Reactome via Pathway Interaction Database)",
				"63");
		pgkbPathwayCount.put("Cleavage of the damaged purine - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Cleavage of the damaged pyrimidine - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("Clomipramine Pathway", "9");
		pgkbPathwayCount.put("Clopidogrel Pathway", "23");
		pgkbPathwayCount.put("Codeine and Morphine Pathway", "33");
		pgkbPathwayCount.put("Coenzyme A biosynthesis - (Reactome via Pathway Interaction Database)", "29");
		pgkbPathwayCount.put(
				"Collagen adhesion via alpha 2 beta 1 glycoprotein - (Reactome via Pathway Interaction Database)",
				"15");
		pgkbPathwayCount.put("Collagen adhesion via Gp IV - (Reactome via Pathway Interaction Database)", "11");
		pgkbPathwayCount.put("Collagen-mediated activation cascade - (Reactome via Pathway Interaction Database)",
				"24");
		pgkbPathwayCount.put("Common Pathway - (Reactome via Pathway Interaction Database)", "59");
		pgkbPathwayCount.put("Complement cascade - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Conjugation of benzoate with glycine - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Conjugation of carboxylic acids - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount
				.put("Conjugation of phenylacetate with glutamine - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("Conjugation of salicylate with glycine - (Reactome via Pathway Interaction Database)",
				"8");
		pgkbPathwayCount.put(
				"control of gene expression by vitamin d receptor - (BioCarta via Pathway Interaction Database)", "67");
		pgkbPathwayCount.put(
				"control of skeletal myogenesis by hdac and calcium/calmodulin-dependent kinase (camk) - (BioCarta via Pathway Interaction Database)",
				"32");
		pgkbPathwayCount.put(
				"Conversion from APC/C:Cdc20 to APC/C:Cdh1 in late anaphase - (Reactome via Pathway Interaction Database)",
				"27");
		pgkbPathwayCount.put("Conversion of cytosolic inosine 5'-monophosphate", "8");
		pgkbPathwayCount.put("Conversion of cytosolic inosine 5'-monophosphate (IMP)", "8");
		pgkbPathwayCount.put(
				"Conversion of Fatty Acyl-CoA to Phosphatidic Acid - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Conversion of Phosphatidic Acid to Diacylglycerol - (Reactome via Pathway Interaction Database)",
				"10");
		pgkbPathwayCount.put(
				"Cooperation of Prefoldin and TriC/CCT  in actin and tubulin folding - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("COPI Mediated Transport - (Reactome via Pathway Interaction Database)", "23");
		pgkbPathwayCount.put(
				"COPII (Coat Protein 2) Mediated Vesicle Transport - (Reactome via Pathway Interaction Database)",
				"12");
		pgkbPathwayCount.put(
				"Coregulation of Androgen receptor activity - (Pathway Interaction Database NCI-Nature Curated)",
				"202");
		pgkbPathwayCount.put("corticosteroids and cardioprotection - (BioCarta via Pathway Interaction Database)",
				"82");
		pgkbPathwayCount.put("COX reactions - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("Creatine metabolism - (Reactome via Pathway Interaction Database)", "15");
		pgkbPathwayCount.put("Creation of C4 and C2 activators - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("CREB phosphorylation - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put("cRNA Synthesis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("ctcf: first multivalent nuclear factor - (BioCarta via Pathway Interaction Database)",
				"56");
		pgkbPathwayCount.put("cxcr4 signaling pathway - (BioCarta via Pathway Interaction Database)", "49");
		pgkbPathwayCount.put(
				"Cyclin A/B1 associated events during G2/M transition - (Reactome via Pathway Interaction Database)",
				"22");
		pgkbPathwayCount.put(
				"Cyclin A:Cdk2-associated events at S phase entry - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("Cyclin B2 mediated events - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Cyclin D associated events in G1 - (Reactome via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put(
				"Cyclin E associated events during G1/S transition - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put("cyclin e destruction pathway - (BioCarta via Pathway Interaction Database)", "28");
		pgkbPathwayCount.put(
				"cycling of ran in nucleocytoplasmic transport - (BioCarta via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put("cyclins and cell cycle regulation - (BioCarta via Pathway Interaction Database)", "55");
		pgkbPathwayCount.put("Cyclophosphamide Pathway", "55");
		pgkbPathwayCount.put("CYP2E1 reactions - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put(
				"cystic fibrosis transmembrane conductance regulator (cftr) and beta 2 adrenergic receptor (b2ar) pathway - (BioCarta via Pathway Interaction Database)",
				"53");
		pgkbPathwayCount.put("Cytochrome c-mediated apoptotic response - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Cytochrome p450 - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount
				.put("Cytochrome P450 - arranged by substrate type - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Cytosolic sulfonation of small molecules - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put("Cytosolic tRNA aminoacylation - (Reactome via Pathway Interaction Database)", "75");
		pgkbPathwayCount.put("d4gdi signaling pathway - (BioCarta via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put("DARPP-32 events - (Reactome via Pathway Interaction Database)", "17");
		pgkbPathwayCount.put("dATP formation - (Reactome via Pathway Interaction Database)", "22");
		pgkbPathwayCount.put("De novo synthesis of IMP - (Reactome via Pathway Interaction Database)", "35");
		pgkbPathwayCount.put(
				"De novo synthesis of the pyrimidine ring and conversion to UMP - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put("Death Receptor  Signalling - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Debranching enzyme transfers 3-glucose blocks from branches in limit dextrin - (Reactome via Pathway Interaction Database)",
				"5");
		pgkbPathwayCount.put(
				"Degradation of beta-catenin by the destruction complex - (Reactome via Pathway Interaction Database)",
				"91");
		pgkbPathwayCount.put(
				"degradation of the rar and rxr by the proteasome - (BioCarta via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put("Deoxyribonucleotide salvage - (Reactome via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put(
				"Depolarization of the Presynaptic Terminal Triggers the Opening of Calcium Channels - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Depurination - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Depyrimidination - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("deregulation of cdk5 in alzheimers disease - (BioCarta via Pathway Interaction Database)",
				"18");
		pgkbPathwayCount.put("dGTP formation - (Reactome via Pathway Interaction Database)", "22");
		pgkbPathwayCount.put("Diabetes pathways - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put("dicer pathway - (BioCarta via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Digestion of dietary carbohydrate - (Reactome via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put("Digestion of dietary lipid - (Reactome via Pathway Interaction Database)", "17");
		pgkbPathwayCount.put(
				"Dihydroxyacetone phosphate is isomerized to form glyceraldehyde-3-phosphate - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Disinhibition of SNARE formation - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("Displacement of DNA glycosylase by  APE1 - (Reactome via Pathway Interaction Database)",
				"19");
		pgkbPathwayCount.put("Dissolution of Fibrin Clot - (Reactome via Pathway Interaction Database)", "26");
		pgkbPathwayCount.put("Diuretics Pathway", "53");
		pgkbPathwayCount.put("DNA Damage Bypass - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("DNA Damage Recognition in GG-NER - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("DNA Damage Reversal - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("DNA Repair - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("DNA Replication - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("DNA replication initiation - (Reactome via Pathway Interaction Database)", "14");
		pgkbPathwayCount.put("DNA Replication Pre-Initiation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("DNA strand elongation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Dopamine clearance from the synaptic cleft - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Dopamine Neurotransmitter Release Cycle - (Reactome via Pathway Interaction Database)",
				"16");
		pgkbPathwayCount.put("Dopamine receptors - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount
				.put("double stranded rna induced gene expression - (BioCarta via Pathway Interaction Database)", "37");
		pgkbPathwayCount.put("Double-Strand Break Repair - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Down-stream signal transduction - (Reactome via Pathway Interaction Database)", "22");
		pgkbPathwayCount.put(
				"downregulated of mta-3 in er-negative breast tumors - (BioCarta via Pathway Interaction Database)",
				"40");
		pgkbPathwayCount.put(
				"Downstream signaling in naïve CD8+ T cells - (Pathway Interaction Database NCI-Nature Curated)",
				"155");
		pgkbPathwayCount.put("Downstream TCR signaling - (Reactome via Pathway Interaction Database)", "49");
		pgkbPathwayCount.put("Doxepin Pathway", "10");
		pgkbPathwayCount.put("Doxorubicin Pathway", "68");
		pgkbPathwayCount.put("Doxorubicin Pathway (Cancer Cell)", "45");
		pgkbPathwayCount.put("Doxorubicin Pathway (Cardiomyocyte Cell)", "57");
		pgkbPathwayCount.put("Dual incision reaction in GG-NER - (Reactome via Pathway Interaction Database)", "59");
		pgkbPathwayCount.put("Dual incision reaction in TC-NER - (Reactome via Pathway Interaction Database)", "70");
		pgkbPathwayCount.put("E-cadherin signaling events - (Pathway Interaction Database NCI-Nature Curated)", "74");
		pgkbPathwayCount
				.put("E-cadherin signaling in keratinocytes - (Pathway Interaction Database NCI-Nature Curated)", "71");
		pgkbPathwayCount.put(
				"E-cadherin signaling in the nascent adherens junction - (Pathway Interaction Database NCI-Nature Curated)",
				"84");
		pgkbPathwayCount.put("E2F mediated regulation of DNA replication - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"E2F-enabled inhibition of pre-replication complex formation - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put("e2f1 destruction pathway - (BioCarta via Pathway Interaction Database)", "30");
		pgkbPathwayCount.put("Early Phase of HIV Life Cycle - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Effects of Botulinum toxin - (Pathway Interaction Database NCI-Nature Curated)", "18");
		pgkbPathwayCount.put(
				"effects of calcineurin in keratinocyte differentiation - (BioCarta via Pathway Interaction Database)",
				"43");
		pgkbPathwayCount.put("Effects of IP3 - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Effects on Ca++ levels - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("egf signaling pathway - (BioCarta via Pathway Interaction Database)", "57");
		pgkbPathwayCount.put("EGFR downregulation - (Reactome via Pathway Interaction Database)", "35");
		pgkbPathwayCount.put("EGFR Inhibitor Pathway", "137");
		pgkbPathwayCount.put("EGFR interacts with phospholipase C-gamma - (Reactome via Pathway Interaction Database)",
				"11");
		pgkbPathwayCount.put(
				"EGFR-dependent Endothelin signaling events - (Pathway Interaction Database NCI-Nature Curated)", "46");
		pgkbPathwayCount.put("eicosanoid metabolism - (BioCarta via Pathway Interaction Database)", "70");
		pgkbPathwayCount.put("Eicosanoids - (Reactome via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put("Electric Transmission Across Gap Junctions - (Reactome via Pathway Interaction Database)",
				"15");
		pgkbPathwayCount.put("Electron Transport Chain - (Reactome via Pathway Interaction Database)", "139");
		pgkbPathwayCount.put("Elevation of cytosolic Ca++ levels - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put(
				"Elongation and Processing of Capped Transcripts - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Elongation arrest and recovery - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Elongation of Intron-Containing Transcripts and co-transcriptional mRNA splicing - (Reactome via Pathway Interaction Database)",
				"111");
		pgkbPathwayCount.put(
				"endocytotic role of ndk phosphins and dynamin - (BioCarta via Pathway Interaction Database)", "34");
		pgkbPathwayCount.put("Endogenous sterols - (Reactome via Pathway Interaction Database)", "36");
		pgkbPathwayCount.put("Endothelins - (Pathway Interaction Database NCI-Nature Curated)", "102");
		pgkbPathwayCount.put("eNOS activation - (Reactome via Pathway Interaction Database)", "22");
		pgkbPathwayCount.put("eNOS activation and regulation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("eNOS acylation cycle - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put(
				"Entry of Influenza Virion into Host Cell via Endocytosis - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Enzymatic degradation of dopamine by COMT - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount.put(
				"Enzymatic degradation of Dopamine by monoamine oxidase - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount.put("EPHA forward signaling - (Pathway Interaction Database NCI-Nature Curated)", "47");
		pgkbPathwayCount.put("EPHA2 forward signaling - (Pathway Interaction Database NCI-Nature Curated)", "38");
		pgkbPathwayCount.put("EPHB forward signaling - (Pathway Interaction Database NCI-Nature Curated)", "79");
		pgkbPathwayCount.put("Ephrin A  reverse signaling - (Pathway Interaction Database NCI-Nature Curated)", "31");
		pgkbPathwayCount.put("Ephrin B reverse signaling - (Pathway Interaction Database NCI-Nature Curated)", "59");
		pgkbPathwayCount.put("EphrinA-EPHA pathway - (Pathway Interaction Database NCI-Nature Curated)", "1");
		pgkbPathwayCount.put("EphrinB-EPHB pathway - (Pathway Interaction Database NCI-Nature Curated)", "1");
		pgkbPathwayCount.put("epo signaling pathway - (BioCarta via Pathway Interaction Database)", "30");
		pgkbPathwayCount.put("EPO signaling pathway - (Pathway Interaction Database NCI-Nature Curated)", "72");
		pgkbPathwayCount.put("er associated degradation (erad) pathway - (BioCarta via Pathway Interaction Database)",
				"31");
		pgkbPathwayCount.put("ER to Golgi Transport - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("ERK activation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"erk and pi-3 kinase are necessary for collagen binding in corneal epithelia - (BioCarta via Pathway Interaction Database)",
				"63");
		pgkbPathwayCount.put("ERK/MAPK targets - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("ERK1 activation - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("erk1/erk2 mapk signaling pathway - (BioCarta via Pathway Interaction Database)", "60");
		pgkbPathwayCount.put("ERK2 activation - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("ERKs are inactivated - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Erlotinib Pathway", "29");
		pgkbPathwayCount.put(
				"erythropoietin mediated neuroprotection through nf-kb - (BioCarta via Pathway Interaction Database)",
				"35");
		pgkbPathwayCount.put("Ester hydrolysis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Estrogen biosynthesis - (Reactome via Pathway Interaction Database)", "15");
		pgkbPathwayCount.put("Estrogen Metabolism Pathway", "39");
		pgkbPathwayCount.put(
				"estrogen responsive protein efp controls cell cycle and breast tumors growth - (BioCarta via Pathway Interaction Database)",
				"30");
		pgkbPathwayCount.put("Ethanol catabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Ethanol is oxidized by NAD+ to form acetaldehyde", "16");
		pgkbPathwayCount.put("Etoposide Pathway", "46");
		pgkbPathwayCount.put("eukaryotic protein translation - (BioCarta via Pathway Interaction Database)", "31");
		pgkbPathwayCount.put("Eukaryotic Translation Elongation - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("Eukaryotic Translation Initiation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Eukaryotic Translation Termination - (Reactome via Pathway Interaction Database)", "153");
		pgkbPathwayCount.put("Exocytosis of Alpha granule - (Reactome via Pathway Interaction Database)", "145");
		pgkbPathwayCount.put("Exocytosis of Dense granule - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put(
				"Export of Viral Ribonucleoproteins from Nucleus - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Extension of Telomeres - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Extrinsic Pathway - (Reactome via Pathway Interaction Database)", "33");
		pgkbPathwayCount.put("Extrinsic Pathway for Apoptosis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("extrinsic prothrombin activation pathway - (BioCarta via Pathway Interaction Database)",
				"60");
		pgkbPathwayCount.put("fas signaling pathway (cd95) - (BioCarta via Pathway Interaction Database)", "75");
		pgkbPathwayCount.put("FAS signaling pathway (CD95) - (Pathway Interaction Database NCI-Nature Curated)", "110");
		pgkbPathwayCount.put("FasL/ CD95L signaling - (Reactome via Pathway Interaction Database)", "20");
		pgkbPathwayCount.put("Fatty acids - (Reactome via Pathway Interaction Database)", "17");
		pgkbPathwayCount.put("Fatty Acyl-CoA Biosynthesis - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put(
				"fc epsilon receptor i signaling in mast cells - (BioCarta via Pathway Interaction Database)", "113");
		pgkbPathwayCount.put("FGF signaling pathway - (Pathway Interaction Database NCI-Nature Curated)", "209");
		pgkbPathwayCount.put("FGFR ligand binding and activation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("FGFR1 ligand binding and activation - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("FGFR1b ligand binding and activation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"FGFR1c and Klotho ligand binding and activation - (Reactome via Pathway Interaction Database)", "57");
		pgkbPathwayCount.put("FGFR1c ligand binding and activation - (Reactome via Pathway Interaction Database)",
				"44");
		pgkbPathwayCount.put("FGFR2 ligand binding and activation - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("FGFR2b ligand binding and activation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("FGFR2c ligand binding and activation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("FGFR3 ligand binding and activation - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("FGFR3b ligand binding and activation - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put("FGFR3c ligand binding and activation - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put("FGFR4 ligand binding and activation - (Reactome via Pathway Interaction Database)", "22");
		pgkbPathwayCount.put("fibrinolysis pathway - (BioCarta via Pathway Interaction Database)", "58");
		pgkbPathwayCount.put("Fluoropyrimidine Pathway", "82");
		pgkbPathwayCount.put("Fluoxetine Pathway", "16");
		pgkbPathwayCount.put("Fluvastatin Pathway", "33");
		pgkbPathwayCount.put(
				"fmlp induced chemokine gene expression in hmc-1 cells - (BioCarta via Pathway Interaction Database)",
				"88");
		pgkbPathwayCount.put("FMO oxidizes nucleophiles - (Reactome via Pathway Interaction Database)", "28");
		pgkbPathwayCount.put("FMO reactions - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Folding of actin by CCT/TriC - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount
				.put("Formation and Maturation of mRNA Transcript - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Formation of a pool of free 40S subunits - (Reactome via Pathway Interaction Database)",
				"179");
		pgkbPathwayCount.put("Formation of Acetoacetic Acid - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("Formation of annular gap junctions - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Formation of apoptosome - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("Formation of ATP by chemiosmotic coupling - (Reactome via Pathway Interaction Database)",
				"75");
		pgkbPathwayCount
				.put("Formation of Cytosolic Glycerol-3-phosphate - (Reactome via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put("Formation of editosomes by ADAR proteins - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount
				.put("Formation of Fibrin Clot (Clotting Cascade) - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Formation of HIV-1 elongation complex containing HIV-1 Tat - (Reactome via Pathway Interaction Database)",
				"101");
		pgkbPathwayCount.put("Formation of incision complex in GG-NER - (Reactome via Pathway Interaction Database)",
				"59");
		pgkbPathwayCount.put("Formation of PAPS - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Formation of Platelet plug - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Formation of RNA Pol II elongation complex - (Reactome via Pathway Interaction Database)",
				"103");
		pgkbPathwayCount.put("Formation of the active cofactor", "6");
		pgkbPathwayCount.put("Formation of the Early Elongation Complex - (Reactome via Pathway Interaction Database)",
				"86");
		pgkbPathwayCount.put("Formation of the Editosome - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put(
				"Formation of the HIV-1 Early Elongation Complex - (Reactome via Pathway Interaction Database)", "86");
		pgkbPathwayCount.put("Formation of the ternary complex", "120");
		pgkbPathwayCount.put(
				"Formation of transcription-coupled NER (TC-NER) repair complex - (Reactome via Pathway Interaction Database)",
				"70");
		pgkbPathwayCount.put(
				"Formation of tubulin folding intermediates by CCT/TriC - (Reactome via Pathway Interaction Database)",
				"15");
		pgkbPathwayCount.put("fosb gene expression and drug abuse - (BioCarta via Pathway Interaction Database)", "36");
		pgkbPathwayCount.put("FOXA transcription factor networks - (Pathway Interaction Database NCI-Nature Curated)",
				"1");
		pgkbPathwayCount.put("FOXA1 transcription factor network - (Pathway Interaction Database NCI-Nature Curated)",
				"122");
		pgkbPathwayCount.put(
				"FOXA2 and FOXA3 transcription factor networks - (Pathway Interaction Database NCI-Nature Curated)",
				"113");
		pgkbPathwayCount.put("FOXM1 transcription factor network - (Pathway Interaction Database NCI-Nature Curated)",
				"110");
		pgkbPathwayCount.put("FoxO family signaling - (Pathway Interaction Database NCI-Nature Curated)", "59");
		pgkbPathwayCount.put("Frs2-mediated activation - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("Fructose 6-phosphate and ATP react to form fructose 2", "7");
		pgkbPathwayCount.put("Fructose catabolism - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Further platelet releasate - (Reactome via Pathway Interaction Database)", "33");
		pgkbPathwayCount
				.put("Fusion and Uncoating of the Influenza Virion - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Fusion of the Influenza Virion to the Host Cell Endosome - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("G alpha 12 cascade - (Reactome via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put("G alpha Q cascade - (Reactome via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put(
				"G(s)-alpha mediated events in glucagon signalling - (Reactome via Pathway Interaction Database)",
				"26");
		pgkbPathwayCount.put("G-protein activation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("G-protein cascades - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("G-protein mediated events - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("g-protein signaling through tubby proteins - (BioCarta via Pathway Interaction Database)",
				"25");
		pgkbPathwayCount.put(
				"g-secretase mediated erbb4 signaling pathway - (BioCarta via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("G1 Phase - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("G1/S DNA Damage Checkpoints - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("G1/S Transition - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("G2 Phase - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("G2/M Checkpoints - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("G2/M DNA damage checkpoint - (Reactome via Pathway Interaction Database)", "14");
		pgkbPathwayCount.put("G2/M DNA replication checkpoint - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put("G2/M Transition - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Gab1 signalosome - (Reactome via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put("Galactose catabolism - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put(
				"gamma-aminobutyric acid receptor life cycle pathway - (BioCarta via Pathway Interaction Database)",
				"23");
		pgkbPathwayCount.put("Gamma-carboxylation", "1");
		pgkbPathwayCount.put("Gamma-carboxylation of protein precursors - (Reactome via Pathway Interaction Database)",
				"66");
		pgkbPathwayCount.put("Gap junction assembly - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("Gap junction degradation - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Gap junction trafficking - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Gap junction trafficking and regulation - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Gap-filling DNA repair synthesis and ligation in GG-NER - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put(
				"Gap-filling DNA repair synthesis and ligation in TC-NER - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount.put(
				"gata3 participate in activating the th2 cytokine genes expression - (BioCarta via Pathway Interaction Database)",
				"61");
		pgkbPathwayCount.put("Gefitinib Pathway", "28");
		pgkbPathwayCount.put("Gemcitabine Pathway", "22");
		pgkbPathwayCount.put("Gene Expression - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("generation of amyloid b-peptide by ps1 - (BioCarta via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put("Generation of second messenger molecules - (Reactome via Pathway Interaction Database)",
				"20");
		pgkbPathwayCount.put("Generic Transcription Pathway - (Reactome via Pathway Interaction Database)", "36");
		pgkbPathwayCount.put("Global Genomic NER (GG-NER) - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Glucagon signaling in metabolic regulation - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Glucocorticoid biosynthesis - (Reactome via Pathway Interaction Database)", "17");
		pgkbPathwayCount.put("Glucocorticoid Pathway (HPA Axis)", "7");
		pgkbPathwayCount.put("Glucocorticoid Pathway (Peripheral Tissue)", "32");
		pgkbPathwayCount.put("Glucocorticoid Pathway - Transcription Regulation", "18");
		pgkbPathwayCount.put("Gluconeogenesis - (Reactome via Pathway Interaction Database)", "58");
		pgkbPathwayCount.put("Glucose + ATP = glucose-6-phosphate + ADP - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put(
				"Glucose 6-phosphate is isomerized to form fructose-6-phosphate - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put(
				"Glucose is carried across the plasma membrane by a glucose transport protein (GLUT) - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put("Glucose metabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Glucose Regulation of Insulin Secretion - (Reactome via Pathway Interaction Database)",
				"18");
		pgkbPathwayCount.put("Glucose uptake - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Glucuronidation - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Glutamate Neurotransmitter Release Cycle - (Reactome via Pathway Interaction Database)",
				"36");
		pgkbPathwayCount.put("Glutathione conjugation - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Glutathione synthesis - (Reactome via Pathway Interaction Database)", "37");
		pgkbPathwayCount.put("Glyceraldehyde 3-phosphate", "4");
		pgkbPathwayCount.put("Glycogen branching enzyme transfers terminal alpha(1", "16");
		pgkbPathwayCount.put("Glycogen breakdown (glycogenolysis) - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put(
				"Glycogen synthase catalyzes the addition of glucose residues to the non-reducing end of a (1", "8");
		pgkbPathwayCount.put("Glycogen synthesis - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Glycogen-glycogenin reacts with n orthophosphates", "7");
		pgkbPathwayCount.put("Glycogenin catalyzes the synthesis of an oligo(1", "4");
		pgkbPathwayCount.put("Glycolysis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Glycoprotein hormones - (Reactome via Pathway Interaction Database)", "33");
		pgkbPathwayCount.put("Glyoxylate metabolism - (Reactome via Pathway Interaction Database)", "11");
		pgkbPathwayCount.put("Glypican 1 network - (Pathway Interaction Database NCI-Nature Curated)", "107");
		pgkbPathwayCount.put("Glypican 2 network - (Pathway Interaction Database NCI-Nature Curated)", "4");
		pgkbPathwayCount.put("Glypican 3 network - (Pathway Interaction Database NCI-Nature Curated)", "31");
		pgkbPathwayCount.put("Glypican pathway - (Pathway Interaction Database NCI-Nature Curated)", "1");
		pgkbPathwayCount.put(
				"Golgi Cisternae Pericentriolar Stack Reorganization - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put("Golgi to ER Retrograde Transport - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("granzyme a mediated apoptosis pathway - (BioCarta via Pathway Interaction Database)",
				"75");
		pgkbPathwayCount.put("Grb2 events in EGFR signaling - (Reactome via Pathway Interaction Database)", "14");
		pgkbPathwayCount.put(
				"Grb2:SOS provides linkage to MAPK signaling for Intergrins - (Reactome via Pathway Interaction Database)",
				"20");
		pgkbPathwayCount.put("growth hormone signaling pathway - (BioCarta via Pathway Interaction Database)", "68");
		pgkbPathwayCount.put(
				"GTP hydrolysis and joining of the 60S ribosomal subunit - (Reactome via Pathway Interaction Database)",
				"194");
		pgkbPathwayCount.put("Guanine formation - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put("HDL-mediated lipid transport - (Reactome via Pathway Interaction Database)", "30");
		pgkbPathwayCount.put(
				"Hedgehog signaling events mediated by Gli proteins - (Pathway Interaction Database NCI-Nature Curated)",
				"122");
		pgkbPathwayCount.put("Heme biosynthesis - (Reactome via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put("hemoglobins chaperone - (BioCarta via Pathway Interaction Database)", "64");
		pgkbPathwayCount.put("Hemostasis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Hexose uptake - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put(
				"HIF-1-alpha transcription factor network - (Pathway Interaction Database NCI-Nature Curated)", "197");
		pgkbPathwayCount.put("Histamine receptors - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Histidine catabolism - (Reactome via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put("HIV Infection - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("HIV Life Cycle - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"hiv-1 defeats host-mediated resistance by cem15 - (BioCarta via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put("HIV-1 elongation arrest and recovery - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount
				.put("hiv-1 nef: negative effector of fas and tnf - (BioCarta via Pathway Interaction Database)", "75");
		pgkbPathwayCount.put(
				"HIV-1 Nef: Negative effector of Fas and TNF-alpha - (Pathway Interaction Database NCI-Nature Curated)",
				"75");
		pgkbPathwayCount.put("HIV-1 Transcription Elongation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("HIV-1 Transcription Initiation - (Reactome via Pathway Interaction Database)", "70");
		pgkbPathwayCount.put("HIV-1 Transcription Pre-Initiation - (Reactome via Pathway Interaction Database)", "70");
		pgkbPathwayCount.put("Homologous DNA pairing and strand exchange - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Homologous Recombination Repair - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Homologous recombination repair of replication-independent double-strand breaks - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("hop pathway in cardiac development - (BioCarta via Pathway Interaction Database)", "26");
		pgkbPathwayCount.put("Hormone biosynthesis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Hormone ligand-binding receptors - (Reactome via Pathway Interaction Database)", "44");
		pgkbPathwayCount.put(
				"Hormone-sensitive lipase (HSL)-mediated triacylglycerol hydrolysis - (Reactome via Pathway Interaction Database)",
				"18");
		pgkbPathwayCount.put("Host Interactions of HIV factors - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Host Interactions with Influenza Factors - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("how does salmonella hijack a cell - (BioCarta via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put(
				"how progesterone initiates the oocyte maturation - (BioCarta via Pathway Interaction Database)", "77");
		pgkbPathwayCount.put(
				"human cytomegalovirus and map kinase pathways - (BioCarta via Pathway Interaction Database)", "30");
		pgkbPathwayCount.put("Hydrolases - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Hydrolysis of cytosolic nucleoside 5'- and 3'-monophosphates by 5'", "3");
		pgkbPathwayCount.put("Hydrolysis of cytosolic nucleoside 5'-monophosphates by 5'-nucleotidase", "2");
		pgkbPathwayCount.put(
				"Hydrolysis of cytosolic nucleoside 5'-monophosphates by 5'-nucleotidase cytosolic IA - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put(
				"Hydrolysis of cytosolic nucleoside 5'-monophosphates by 5'-nucleotidase cytosolic IB - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put(
				"Hydrolysis of cytosolic nucleoside 5'-monophosphates by 5'-nucleotidase cytosolic II - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put(
				"Hydrolysis of extracellular nucleoside 5'-monophosphates by plasma membrane-bound 5'-nucleotidase - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Hydrolysis of mitochondrial nucleoside 5'- and 3'-monophosphates by 5'", "2");
		pgkbPathwayCount.put(
				"Hydrolysis of nucleoside 5'-monophosphates to nucleosides plus orthophosphate - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Hypoxanthine formation - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put(
				"hypoxia and p53 in the cardiovascular system - (BioCarta via Pathway Interaction Database)", "69");
		pgkbPathwayCount.put(
				"hypoxia-inducible factor in the cardivascular system - (BioCarta via Pathway Interaction Database)",
				"35");
		pgkbPathwayCount.put(
				"Hypoxic and oxygen homeostasis regulation of HIF-1-alpha - (Pathway Interaction Database NCI-Nature Curated)",
				"49");
		pgkbPathwayCount.put("Hypusine synthesis from eIF5A-lysine - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Ibuprofen Pathway", "42");
		pgkbPathwayCount.put("ifn alpha signaling pathway - (BioCarta via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put("ifn gamma signaling pathway - (BioCarta via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("IFN-gamma pathway - (Pathway Interaction Database NCI-Nature Curated)", "95");
		pgkbPathwayCount.put("Ifosfamide Pathway", "26");
		pgkbPathwayCount.put("igf-1 signaling pathway - (BioCarta via Pathway Interaction Database)", "67");
		pgkbPathwayCount.put("IGF1 pathway - (Pathway Interaction Database NCI-Nature Curated)", "66");
		pgkbPathwayCount.put("il 2 signaling pathway - (BioCarta via Pathway Interaction Database)", "34");
		pgkbPathwayCount.put("il 3 signaling pathway - (BioCarta via Pathway Interaction Database)", "40");
		pgkbPathwayCount.put("il 4 signaling pathway - (BioCarta via Pathway Interaction Database)", "81");
		pgkbPathwayCount.put("il 6 signaling pathway - (BioCarta via Pathway Interaction Database)", "32");
		pgkbPathwayCount.put("il-10 anti-inflammatory signaling pathway - (BioCarta via Pathway Interaction Database)",
				"21");
		pgkbPathwayCount.put(
				"il-2 receptor beta chain in t cell activation - (BioCarta via Pathway Interaction Database)", "91");
		pgkbPathwayCount.put("il-7 signal transduction - (BioCarta via Pathway Interaction Database)", "50");
		pgkbPathwayCount.put("IL1-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)", "56");
		pgkbPathwayCount.put(
				"il12 and stat4 dependent signaling pathway in th1 development - (BioCarta via Pathway Interaction Database)",
				"22");
		pgkbPathwayCount.put("IL12 signaling mediated by STAT4 - (Pathway Interaction Database NCI-Nature Curated)",
				"101");
		pgkbPathwayCount.put("IL12-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"170");
		pgkbPathwayCount
				.put("IL2 signaling events mediated by PI3K - (Pathway Interaction Database NCI-Nature Curated)", "92");
		pgkbPathwayCount.put(
				"IL2 signaling events mediated by STAT5 - (Pathway Interaction Database NCI-Nature Curated)", "55");
		pgkbPathwayCount.put("IL2-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"114");
		pgkbPathwayCount.put("il22 soluble receptor signaling pathway - (BioCarta via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put("IL23-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"78");
		pgkbPathwayCount.put("IL27-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"50");
		pgkbPathwayCount.put("IL4-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"150");
		pgkbPathwayCount.put("IL6-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"108");
		pgkbPathwayCount.put("Imatinib Pathway", "31");
		pgkbPathwayCount.put("Imipramine/Desipramine Pathway", "9");
		pgkbPathwayCount.put(
				"Immunoregulatory interactions between a Lymphoid and a non-Lymphoid cell - (Reactome via Pathway Interaction Database)",
				"122");
		pgkbPathwayCount.put(
				"Import of palmitoyl-CoA into the mitochondrial matrix - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put(
				"Inactivation of APC/C via direct inhibition of the APC/C complex - (Reactome via Pathway Interaction Database)",
				"29");
		pgkbPathwayCount.put(
				"inactivation of gsk3 by akt causes accumulation of b-catenin in alveolar macrophages - (BioCarta via Pathway Interaction Database)",
				"78");
		pgkbPathwayCount.put(
				"induction of apoptosis through dr3 and dr4/5 death receptors - (BioCarta via Pathway Interaction Database)",
				"60");
		pgkbPathwayCount.put(
				"influence of ras and rho proteins on g1 to s transition - (BioCarta via Pathway Interaction Database)",
				"65");
		pgkbPathwayCount.put("Influenza Infection - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Influenza Life Cycle - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Influenza Viral RNA Transcription and Replication - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Influenza Virus Induced Apoptosis - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put(
				"inhibition of cellular proliferation by gleevec - (BioCarta via Pathway Interaction Database)", "52");
		pgkbPathwayCount.put(
				"Inhibition of Host mRNA Processing and RNA Silencing - (Reactome via Pathway Interaction Database)",
				"8");
		pgkbPathwayCount.put("Inhibition of HSL - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put(
				"inhibition of huntingtons disease neurodegeneration by histone deacetylase inhibitors - (BioCarta via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put("Inhibition of IFN-beta - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Inhibition of Interferon Synthesis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("inhibition of matrix metalloproteinases - (BioCarta via Pathway Interaction Database)",
				"54");
		pgkbPathwayCount.put("Inhibition of PKR - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put(
				"Inhibition of replication initiation of damaged DNA by Rb/E2F1 - (Reactome via Pathway Interaction Database)",
				"17");
		pgkbPathwayCount.put(
				"Inhibition of the proteolytic activity of APC/C required for the onset of anaphase by mitotic spindle checkpoint components - (Reactome via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put("Inhibition of TSC complex formation by PKB - (Reactome via Pathway Interaction Database)",
				"10");
		pgkbPathwayCount.put("Initial triggering of complement - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("Innate Immunity Signaling - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Inosine formation - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put(
				"Insulin effects increased synthesis of Xylulose-5-Phosphate - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put("Insulin Pathway - (Pathway Interaction Database NCI-Nature Curated)", "77");
		pgkbPathwayCount.put("Insulin receptor recycling - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("Insulin receptor signalling cascade - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("insulin signaling pathway - (BioCarta via Pathway Interaction Database)", "32");
		pgkbPathwayCount.put("Insulin Synthesis and Secretion - (Reactome via Pathway Interaction Database)", "64");
		pgkbPathwayCount.put("Insulin-mediated glucose transport - (Pathway Interaction Database NCI-Nature Curated)",
				"41");
		pgkbPathwayCount.put("Integration of energy metabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Integration of provirus - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put(
				"Integration of viral DNA into host genomic DNA - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Integrin alphaIIbbeta3 signaling - (Reactome via Pathway Interaction Database)", "17");
		pgkbPathwayCount.put("Integrin cell surface interactions - (Reactome via Pathway Interaction Database)", "143");
		pgkbPathwayCount.put("integrin signaling pathway - (BioCarta via Pathway Interaction Database)", "84");
		pgkbPathwayCount.put("Integrins in angiogenesis - (Pathway Interaction Database NCI-Nature Curated)", "106");
		pgkbPathwayCount.put(
				"Interactions of Rev with host cellular proteins - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Interactions of Tat with host cellular proteins - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put(
				"Interactions of Vpr with host cellular proteins - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Interconversion of polyamines - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("internal ribosome entry pathway - (BioCarta via Pathway Interaction Database)", "17");
		pgkbPathwayCount.put("Intrinsic Pathway - (Reactome via Pathway Interaction Database)", "79");
		pgkbPathwayCount.put("Intrinsic Pathway for Apoptosis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("intrinsic prothrombin activation pathway - (BioCarta via Pathway Interaction Database)",
				"97");
		pgkbPathwayCount.put(
				"ion channels and their functional role in vascular endothelium - (BioCarta via Pathway Interaction Database)",
				"67");
		pgkbPathwayCount.put(
				"ionomycin and phorbal ester signaling pathway - (BioCarta via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put("Irinotecan Pathway", "85");
		pgkbPathwayCount.put("IRS activation - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("IRS-mediated signalling - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("IRS-related events - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Isoleucine catabolism - (Reactome via Pathway Interaction Database)", "11");
		pgkbPathwayCount
				.put("JNK signaling in the CD4+ TCR pathway - (Pathway Interaction Database NCI-Nature Curated)", "24");
		pgkbPathwayCount.put("keratinocyte differentiation - (BioCarta via Pathway Interaction Database)", "94");
		pgkbPathwayCount.put("Ketone body metabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"L13a-mediated translational silencing of Ceruloplasmin expression - (Reactome via Pathway Interaction Database)",
				"193");
		pgkbPathwayCount.put("Lagging Strand Synthesis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Lamivudine Pathway", "43");
		pgkbPathwayCount.put("Late Phase of HIV Life Cycle - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"lck and fyn tyrosine kinases in initiation of tcr activation - (BioCarta via Pathway Interaction Database)",
				"84");
		pgkbPathwayCount.put("LDL endocytosis - (Reactome via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put("Leading Strand Synthesis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("lectin induced complement pathway - (BioCarta via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put("Lectin pathway of complement activation - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put("Leucine catabolism - (Reactome via Pathway Interaction Database)", "24");
		pgkbPathwayCount.put("Leukotriene modifiers pathway", "36");
		pgkbPathwayCount.put("Leukotriene synthesis - (Reactome via Pathway Interaction Database)", "47");
		pgkbPathwayCount.put("links between pyk2 and map kinases - (BioCarta via Pathway Interaction Database)", "56");
		pgkbPathwayCount.put("Lipid and lipoprotein metabolism - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Lipoprotein metabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"lissencephaly gene (lis1) in neuronal migration and development - (BioCarta via Pathway Interaction Database)",
				"23");
		pgkbPathwayCount.put(
				"Lissencephaly gene (LIS1) in neuronal migration and development - (Pathway Interaction Database NCI-Nature Curated)",
				"59");
		pgkbPathwayCount.put("Losartan Pathway", "9");
		pgkbPathwayCount.put("Loss of Nlp from mitotic centrosomes - (Reactome via Pathway Interaction Database)",
				"133");
		pgkbPathwayCount.put(
				"Loss of proteins required for interphase microtubule organization from the centrosome - (Reactome via Pathway Interaction Database)",
				"133");
		pgkbPathwayCount.put("LPA receptor mediated events - (Pathway Interaction Database NCI-Nature Curated)", "133");
		pgkbPathwayCount.put("LPA4-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"22");
		pgkbPathwayCount.put("LPS transferred from LBP carrier to CD14 - (Reactome via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put("Lysine catabolism - (Reactome via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put("M Phase - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("M/G1 Transition - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Mal Cascade - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("MAP kinase cascade - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount
				.put("map kinase inactivation of smrt corepressor - (BioCarta via Pathway Interaction Database)", "43");
		pgkbPathwayCount.put("mapkinase signaling pathway - (BioCarta via Pathway Interaction Database)", "115");
		pgkbPathwayCount.put(
				"Maturation of Notch precursor via proteolytic cleavage - (Reactome via Pathway Interaction Database)",
				"18");
		pgkbPathwayCount.put(
				"Mature Notch receptor trafficks to plasma membrane - (Reactome via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put("mcalpain and friends in cell motility - (BioCarta via Pathway Interaction Database)",
				"67");
		pgkbPathwayCount.put(
				"mechanism of acetaminophen activity and toxicity - (BioCarta via Pathway Interaction Database)", "59");
		pgkbPathwayCount.put(
				"mechanism of gene regulation by peroxisome proliferators via ppara - (BioCarta via Pathway Interaction Database)",
				"51");
		pgkbPathwayCount.put(
				"mechanism of protein import into the nucleus - (BioCarta via Pathway Interaction Database)", "17");
		pgkbPathwayCount.put(
				"mechanisms of transcriptional repression by dna methylation - (BioCarta via Pathway Interaction Database)",
				"39");
		pgkbPathwayCount.put("MEK activation - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put(
				"melanocyte development and pigmentation pathway - (BioCarta via Pathway Interaction Database)", "33");
		pgkbPathwayCount.put("Membrane Trafficking - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Metablism of nucleotides - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Metabolism of amino acids - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"metabolism of anandamide an endogenous cannabinoid - (BioCarta via Pathway Interaction Database)",
				"20");
		pgkbPathwayCount.put("Metabolism of bile acids and bile salts - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Metabolism of carbohydrates - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Metabolism of folate and pterines - (Reactome via Pathway Interaction Database)", "23");
		pgkbPathwayCount.put("Metabolism of lipids and lipoproteins - (Reactome via Pathway Interaction Database)",
				"12");
		pgkbPathwayCount.put("Metabolism of nitric oxide - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Metabolism of non-coding RNA - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Metabolism of polyamines - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Metabolism of porphyrins - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Metabolism of proteins - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Metabolism of serotonin - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Metabolism of vitamins and cofactors - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Metabolism of water-soluble vitamins and cofactors - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Metformin Pathway", "76");
		pgkbPathwayCount.put("Methotrexate Pathway", "41");
		pgkbPathwayCount.put("Methotrexate Pathway (Brain Cell)", "25");
		pgkbPathwayCount.put("Methotrexate Pathway (Cancer Cell)", "101");
		pgkbPathwayCount.put("Methylation - (Reactome via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put("Methylene Blue Pathway", "10");
		pgkbPathwayCount.put("mets affect on macrophage differentiation - (BioCarta via Pathway Interaction Database)",
				"57");
		pgkbPathwayCount.put("MicroRNA biogenesis - (Reactome via Pathway Interaction Database)", "43");
		pgkbPathwayCount.put(
				"Microtubule-dependent trafficking of connexons from Golgi to the plasma membrane - (Reactome via Pathway Interaction Database)",
				"5");
		pgkbPathwayCount.put("Mineralocorticoid biosynthesis - (Reactome via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put("Minus-strand DNA synthesis - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Mitochondrial Fatty Acid Beta-Oxidation - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"mitochondrial fatty acid beta-oxidation of saturated fatty acids - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"mitochondrial fatty acid beta-oxidation of unsaturated fatty acids - (Reactome via Pathway Interaction Database)",
				"10");
		pgkbPathwayCount.put("Mitochondrial transcription initiation - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put("Mitochondrial transcription termination - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Mitochondrial tRNA aminoacylation - (Reactome via Pathway Interaction Database)", "89");
		pgkbPathwayCount.put("Mitochondrial Uncoupling Proteins - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Mitotic Metaphase/Anaphase Transition - (Reactome via Pathway Interaction Database)",
				"17");
		pgkbPathwayCount.put("Mitotic Prometaphase - (Reactome via Pathway Interaction Database)", "153");
		pgkbPathwayCount.put("Mitotic Prophase - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Mitotic Spindle Checkpoint - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Mitotic Telophase /Cytokinesis - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("Monoamines are oxidized to aldehydes by MAOA and MAOB", "8");
		pgkbPathwayCount.put("MRN complex relocalizes to nuclear foci - (Reactome via Pathway Interaction Database)",
				"5");
		pgkbPathwayCount.put("mRNA 3'-end processing - (Reactome via Pathway Interaction Database)", "63");
		pgkbPathwayCount.put("mRNA Capping - (Reactome via Pathway Interaction Database)", "58");
		pgkbPathwayCount.put("mRNA Editing - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("mRNA Editing: A to I Conversion - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("mRNA Editing: C to U Conversion - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("mRNA Processing - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("mRNA Splicing - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("mRNA Splicing - Major Pathway - (Reactome via Pathway Interaction Database)", "169");
		pgkbPathwayCount.put("mRNA Splicing - Minor Pathway - (Reactome via Pathway Interaction Database)", "74");
		pgkbPathwayCount.put("mtor signaling pathway - (BioCarta via Pathway Interaction Database)", "84");
		pgkbPathwayCount.put("mTOR signaling pathway - (Pathway Interaction Database NCI-Nature Curated)", "66");
		pgkbPathwayCount.put("mTOR signalling - (Reactome via Pathway Interaction Database)", "22");
		pgkbPathwayCount.put("mTORC1-mediated signalling - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("multi-drug resistance factors - (BioCarta via Pathway Interaction Database)", "22");
		pgkbPathwayCount.put(
				"multi-step regulation of transcription by pitx2 - (BioCarta via Pathway Interaction Database)", "54");
		pgkbPathwayCount.put(
				"multiple antiapoptotic pathways from igf-1r signaling lead to bad phosphorylation - (BioCarta via Pathway Interaction Database)",
				"37");
		pgkbPathwayCount.put("Muscarinic acetylcholine receptors - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Muscle contraction - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Mycophenolic acid Pathway", "44");
		pgkbPathwayCount.put("MyD88 cascade - (Reactome via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put("N-cadherin signaling events - (Pathway Interaction Database NCI-Nature Curated)", "134");
		pgkbPathwayCount.put("N-oxidation of nitrogen compounds - (Reactome via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put("NADE modulates death signalling - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("NADPH regeneration - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Nectin adhesion pathway - (Pathway Interaction Database NCI-Nature Curated)", "59");
		pgkbPathwayCount.put("Nef and signal transduction - (Reactome via Pathway Interaction Database)", "46");
		pgkbPathwayCount.put("Nef Mediated CD4 Down-regulation - (Reactome via Pathway Interaction Database)", "15");
		pgkbPathwayCount.put("Nef Mediated CD8 Down-regulation - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put(
				"Nef mediated downregulation of CD28 cell surface expression - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put(
				"Nef mediated downregulation of MHC class I complex cell surface expression - (Reactome via Pathway Interaction Database)",
				"54");
		pgkbPathwayCount.put(
				"Nef-mediates down modulation of cell surface receptors by recruiting them to clathrin adapters - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Negative Regulation of Glucokinase by Glucokinase Regulatory Protein - (Reactome via Pathway Interaction Database)",
				"25");
		pgkbPathwayCount
				.put("Negative regulation of the PI3K/AKT network - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put(
				"NEP/NS2 Interacts with the Cellular Export Machinery - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount.put("nerve growth factor pathway (ngf) - (BioCarta via Pathway Interaction Database)", "65");
		pgkbPathwayCount
				.put("Neurophilin interactions with VEGF and VEGFR - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put(
				"neuroregulin receptor degredation protein-1 controls erbb3 receptor recycling - (BioCarta via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put(
				"Neurotransmitter Clearance In The Synaptic Cleft - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Neurotransmitter Release Cycle - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Neurotransmitter uptake and Metabolism In Glial Cells - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Neurotrophic factor-mediated Trk receptor signaling - (Pathway Interaction Database NCI-Nature Curated)",
				"96");
		pgkbPathwayCount.put("Nevirapine Pathway", "17");
		pgkbPathwayCount.put("NF-kB is activated and signals survival - (Reactome via Pathway Interaction Database)",
				"26");
		pgkbPathwayCount.put("nf-kb signaling pathway - (BioCarta via Pathway Interaction Database)", "51");
		pgkbPathwayCount.put("nfat and hypertrophy of the heart  - (BioCarta via Pathway Interaction Database)", "79");
		pgkbPathwayCount.put("NFG and proNGF binds to p75NTR - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put(
				"nfkb activation by nontypeable hemophilus influenzae - (BioCarta via Pathway Interaction Database)",
				"61");
		pgkbPathwayCount.put("NGF processing - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("NGF-independant TRKA activation - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("NICD trafficks to nucleus - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Nicotinamide salvaging - (Reactome via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put("Nicotinate metabolism - (Reactome via Pathway Interaction Database)", "25");
		pgkbPathwayCount.put("Nicotine Pathway", "35");
		pgkbPathwayCount.put("Nicotine Pathway (Chromaffin Cell)", "17");
		pgkbPathwayCount.put("Nicotine Pathway (Dopaminergic Neuron)", "93");
		pgkbPathwayCount.put("nitric oxide signaling pathway - (BioCarta via Pathway Interaction Database)", "54");
		pgkbPathwayCount.put("no2-dependent il-12 pathway in nk cells - (BioCarta via Pathway Interaction Database)",
				"21");
		pgkbPathwayCount.put("Noncanonical Wnt signaling pathway - (Pathway Interaction Database NCI-Nature Curated)",
				"59");
		pgkbPathwayCount.put("Nongenotropic Androgen signaling - (Pathway Interaction Database NCI-Nature Curated)",
				"79");
		pgkbPathwayCount.put("Nonhomologous End-joining (NHEJ) - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put(
				"Norepinephrine Neurotransmitter Release Cycle - (Reactome via Pathway Interaction Database)", "32");
		pgkbPathwayCount.put("NOSIP mediated eNOS trafficking - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("NOSTRIN mediated eNOS trafficking - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("Notch receptor binds with a ligand - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Notch-HLH transcription pathway - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("NR transcription pathway - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("NRAGE signals death through JNK - (Reactome via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put("NRIF signals cell death from the nucleus - (Reactome via Pathway Interaction Database)",
				"18");
		pgkbPathwayCount.put("NS1 Mediated Effects on Host Pathways - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Nuclear Events (kinase and transcription factor activation) - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Nuclear import of Rev protein - (Reactome via Pathway Interaction Database)", "36");
		pgkbPathwayCount.put(
				"nuclear receptors coordinate the activities of chromatin remodeling complexes and coactivators to facilitate initiation of transcription in carcinoma cells - (BioCarta via Pathway Interaction Database)",
				"41");
		pgkbPathwayCount.put("Nucleotide Excision Repair - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Nucleotide metabolism - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("null", "246");
		pgkbPathwayCount.put("Olfactory Signaling Pathway - (Reactome via Pathway Interaction Database)", "25");
		pgkbPathwayCount
				.put("Oligomerization of connexins into connexons - (Reactome via Pathway Interaction Database)", "28");
		pgkbPathwayCount.put("Opioid Signalling - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"opposing roles of aif in apoptosis and cell survival - (BioCarta via Pathway Interaction Database)",
				"10");
		pgkbPathwayCount.put("Orc1 removal from chromatin - (Reactome via Pathway Interaction Database)", "100");
		pgkbPathwayCount.put(
				"Orexin and neuropeptides FF and QRFP bind to their respective receptors - (Reactome via Pathway Interaction Database)",
				"8");
		pgkbPathwayCount.put("Ornithine and proline metabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Ornithine metabolism - (Reactome via Pathway Interaction Database)", "14");
		pgkbPathwayCount.put("Osteopontin-mediated events - (Pathway Interaction Database NCI-Nature Curated)", "71");
		pgkbPathwayCount.put(
				"overview of telomerase protein component gene htert transcriptional regulation - (BioCarta via Pathway Interaction Database)",
				"32");
		pgkbPathwayCount.put(
				"overview of telomerase rna component gene hterc transcriptional regulation - (BioCarta via Pathway Interaction Database)",
				"30");
		pgkbPathwayCount.put(
				"Oxidative decarboxylation of alpha-keto-beta-methylvalerate to alpha-methylbutyryl-CoA by branched-chain alpha-ketoacid dehydrogenase - (Reactome via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put(
				"Oxidative decarboxylation of alpha-ketoadipate to glutaryl CoA by alpha-ketoglutarate dehydrogenase - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put(
				"Oxidative decarboxylation of alpha-ketoglutarate to succinyl CoA by alpha-ketoglutarate dehydrogenase - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put(
				"Oxidative decarboxylation of alpha-ketoisocaproate to isovaleryl-CoA by branched-chain alpha-ketoacid dehydrogenase - (Reactome via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put(
				"Oxidative decarboxylation of alpha-ketoisovalerate to isobutyryl-CoA by branched-chain alpha-ketoacid dehydrogenase - (Reactome via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put(
				"Oxidative decarboxylation of pyruvate to acetyl CoA by pyruvate dehydrogenase - (Reactome via Pathway Interaction Database)",
				"19");
		pgkbPathwayCount.put(
				"oxidative stress induced gene expression via nrf2 - (BioCarta via Pathway Interaction Database)",
				"33");
		pgkbPathwayCount.put("Oxidative Stress Regulatory Pathway (Erythrocyte)", "22");
		pgkbPathwayCount.put(
				"p130Cas linkage to MAPK signaling for integrins - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("p38 mapk signaling pathway - (BioCarta via Pathway Interaction Database)", "77");
		pgkbPathwayCount.put("p38 MAPK signaling pathway - (Pathway Interaction Database NCI-Nature Curated)", "41");
		pgkbPathwayCount.put(
				"p38 signaling mediated by MAPKAP kinases - (Pathway Interaction Database NCI-Nature Curated)", "47");
		pgkbPathwayCount.put("p38MAPK events - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("P450 Dehalogenation - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put(
				"P450 Dehydrogenation of alkanes to form alkenes - (Reactome via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put("P450 Epoxidations - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("P450 Hydroxylations - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("P450 oxidation - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("P450 Prostaglandin isomerizations - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("p53 signaling pathway - (BioCarta via Pathway Interaction Database)", "36");
		pgkbPathwayCount.put("p53-Dependent G1 DNA Damage Response - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("p53-Dependent G1/S DNA damage checkpoint - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put("p53-Independent DNA Damage Response - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("p53-Independent G1/S DNA damage checkpoint - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put("p75 NTR receptor-mediated signalling - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("p75(NTR)-mediated signaling - (Pathway Interaction Database NCI-Nature Curated)", "116");
		pgkbPathwayCount.put(
				"p75NTR negatively regulates cell cycle via SC1 - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("p75NTR recruits signalling complexes - (Reactome via Pathway Interaction Database)",
				"24");
		pgkbPathwayCount.put("p75NTR regulates axonogenesis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("p75NTR signals via NF-kB - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Packaging of Eight RNA Segments - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Packaging Of Telomere Ends - (Reactome via Pathway Interaction Database)", "40");
		pgkbPathwayCount.put("Pausing and recovery of elongation - (Reactome via Pathway Interaction Database)", "87");
		pgkbPathwayCount.put("Pausing and recovery of HIV-1 elongation - (Reactome via Pathway Interaction Database)",
				"87");
		pgkbPathwayCount.put(
				"Pausing and recovery of Tat-mediated HIV-1 elongation - (Reactome via Pathway Interaction Database)",
				"85");
		pgkbPathwayCount.put(
				"Paxillin-dependent events mediated by a4b1 - (Pathway Interaction Database NCI-Nature Curated)", "41");
		pgkbPathwayCount.put(
				"Paxillin-independent events mediated by a4b1 and a4b7 - (Pathway Interaction Database NCI-Nature Curated)",
				"51");
		pgkbPathwayCount.put("PDE3B signalling - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("pdgf signaling pathway - (BioCarta via Pathway Interaction Database)", "76");
		pgkbPathwayCount.put("PDGFR-alpha signaling pathway - (Pathway Interaction Database NCI-Nature Curated)", "68");
		pgkbPathwayCount.put("PDGFR-beta signaling pathway - (Pathway Interaction Database NCI-Nature Curated)", "106");
		pgkbPathwayCount.put("PECAM1 interactions - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put(
				"pelp1 modulation of estrogen receptor activity - (BioCarta via Pathway Interaction Database)", "27");
		pgkbPathwayCount.put("Pentose Phosphate Pathway (Erythrocyte)", "14");
		pgkbPathwayCount.put(
				"Pentose phosphate pathway (hexose monophosphate shunt) - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put("Peptide chain elongation - (Reactome via Pathway Interaction Database)", "155");
		pgkbPathwayCount.put("Peptide hormones - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("Peptide ligand-binding receptors - (Reactome via Pathway Interaction Database)", "59");
		pgkbPathwayCount.put("Peroxisomal lipid metabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"pertussis toxin-insensitive ccr5 signaling in macrophage - (BioCarta via Pathway Interaction Database)",
				"41");
		pgkbPathwayCount.put("Phase 1 - Functionalization of compounds - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Phase 1 functionalization - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Phase II conjugation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Phenylalanine and tyrosine catabolism - (Reactome via Pathway Interaction Database)",
				"21");
		pgkbPathwayCount.put("Phenytoin Pathway", "57");
		pgkbPathwayCount.put("phosphatidylcholine biosynthesis pathway - (BioCarta via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put("phospho-PLA2 pathway - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put(
				"Phosphoenolpyruvate and ADP react to form pyruvate and ATP - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put(
				"phosphoinositides and their downstream targets - (BioCarta via Pathway Interaction Database)", "38");
		pgkbPathwayCount.put(
				"phospholipase c delta in phospholipid associated cell signaling - (BioCarta via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put("phospholipase c signaling pathway - (BioCarta via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("phospholipase c-epsilon pathway - (BioCarta via Pathway Interaction Database)", "61");
		pgkbPathwayCount.put("Phospholipase-mediated signalling - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("phospholipids as signalling intermediaries - (BioCarta via Pathway Interaction Database)",
				"74");
		pgkbPathwayCount.put(
				"Phosphorylase kinase activates glycogen phosphorylase - (Reactome via Pathway Interaction Database)",
				"22");
		pgkbPathwayCount.put(
				"Phosphorylated glycogen synthase catalyzes the addition of glucose residues to the non-reducing end of a (1",
				"9");
		pgkbPathwayCount.put("Phosphorylation of CD3 and TCR zeta chains - (Reactome via Pathway Interaction Database)",
				"12");
		pgkbPathwayCount.put(
				"Phosphorylation of cytosolic nucleosides by adenosine kinase - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put(
				"Phosphorylation of cytosolic nucleosides by deoxycytidine kinase - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Phosphorylation of cytosolic nucleosides by thymidine kinase 1", "3");
		pgkbPathwayCount.put(
				"Phosphorylation of cytosolic nucleosides by uridine-cytidine kinase 1 - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put(
				"Phosphorylation of cytosolic nucleosides by uridine-cytidine kinase 2 - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Phosphorylation of Emi1 - (Reactome via Pathway Interaction Database)", "15");
		pgkbPathwayCount.put(
				"phosphorylation of mek1 by cdk5/p35 down regulates the map kinase pathway - (BioCarta via Pathway Interaction Database)",
				"34");
		pgkbPathwayCount.put(
				"Phosphorylation of mitochondrial nucleosides by deoxyguanosine kinase - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount.put("Phosphorylation of mitochondrial nucleosides by thymidine kinase 2", "39");
		pgkbPathwayCount.put(
				"Phosphorylation of nucleosides to form nucleoside 5'-monophosphates - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Phosphorylation of proteins involved in G1/S transition by active Cyclin E:Cdk2 complexes - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put(
				"Phosphorylation of proteins involved in the G2/M transition by Cyclin A:Cdc2 complexes - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put("Phosphorylation of the APC/C - (Reactome via Pathway Interaction Database)", "30");
		pgkbPathwayCount.put("PI3K Cascade - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("PI3K/AKT signalling - (Reactome via Pathway Interaction Database)", "35");
		pgkbPathwayCount.put("PKA activation - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("PKA activation in glucagon signalling - (Reactome via Pathway Interaction Database)",
				"15");
		pgkbPathwayCount.put("PKA-mediated phosphorylation of CREB - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put(
				"PKA-mediated phosphorylation of key metabolic factors - (Reactome via Pathway Interaction Database)",
				"8");
		pgkbPathwayCount.put("PKB-mediated events - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"pkc-catalyzed phosphorylation of inhibitory phosphoprotein of myosin phosphatase - (BioCarta via Pathway Interaction Database)",
				"56");
		pgkbPathwayCount.put(
				"Plasma membrane estrogen receptor signaling - (Pathway Interaction Database NCI-Nature Curated)",
				"99");
		pgkbPathwayCount.put("Plasmalogen biosynthesis - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("Platelet Activation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Platelet activation triggers - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Platelet Adhesion to exposed  collagen - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Platelet Aggregation (Plug Formation) - (Reactome via Pathway Interaction Database)",
				"41");
		pgkbPathwayCount.put("Platelet Aggregation Inhibitor Pathway", "1310");
		pgkbPathwayCount.put("platelet amyloid precursor protein pathway - (BioCarta via Pathway Interaction Database)",
				"67");
		pgkbPathwayCount.put("Platelet degranulation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Platinum Pathway", "65");
		pgkbPathwayCount.put("PLC beta mediated events - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("PLC-gamma1 signalling - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("PLC-mediated hydrolysis - (Reactome via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put("PLC-mediated hydrolysis of PIP2 - (Reactome via Pathway Interaction Database)", "11");
		pgkbPathwayCount.put("PLK1 signaling events - (Pathway Interaction Database NCI-Nature Curated)", "83");
		pgkbPathwayCount.put("PLK2 and PLK4 events - (Pathway Interaction Database NCI-Nature Curated)", "3");
		pgkbPathwayCount.put("PLK3 signaling events - (Pathway Interaction Database NCI-Nature Curated)", "12");
		pgkbPathwayCount.put("Plug Formation - (Reactome via Pathway Interaction Database)", "46");
		pgkbPathwayCount.put("Plus-strand DNA synthesis - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Polo-like kinase mediated events - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put(
				"Polo-like kinase signaling events in the cell cycle - (Pathway Interaction Database NCI-Nature Curated)",
				"1");
		pgkbPathwayCount.put("polyadenylation of mrna - (BioCarta via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put("Polyamines are oxidized to amines", "2");
		pgkbPathwayCount.put("Polymerase switching - (Reactome via Pathway Interaction Database)", "28");
		pgkbPathwayCount.put(
				"Polymerase switching on the C-strand of the telomere - (Reactome via Pathway Interaction Database)",
				"28");
		pgkbPathwayCount.put("Poly{(1", "10");
		pgkbPathwayCount.put("Porphyrin metabolism - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Post-chaperonin tubulin folding pathway - (Reactome via Pathway Interaction Database)",
				"17");
		pgkbPathwayCount.put(
				"Post-Elongation Processing of Intron-Containing pre-mRNA - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Post-Elongation Processing of Intronless pre-mRNA - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount
				.put("Post-Elongation Processing of the Transcript - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Post-translational protein modification - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"PP2A-mediated dephosphorylation of key metabolic factors - (Reactome via Pathway Interaction Database)",
				"8");
		pgkbPathwayCount.put("Pravastatin Pathway", "26");
		pgkbPathwayCount.put(
				"Prefoldin mediated transfer of substrate  to CCT/TriC - (Reactome via Pathway Interaction Database)",
				"69");
		pgkbPathwayCount.put("Pregnenolone biosynthesis - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put(
				"Presenilin action in Notch and Wnt signaling - (Pathway Interaction Database NCI-Nature Curated)",
				"106");
		pgkbPathwayCount.put(
				"Presynaptic phase of homologous DNA pairing and strand exchange - (Reactome via Pathway Interaction Database)",
				"25");
		pgkbPathwayCount.put("prion pathway - (BioCarta via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put(
				"Processing of Capped Intron-Containing Pre-mRNA - (Reactome via Pathway Interaction Database)", "111");
		pgkbPathwayCount.put("Processing of Capped Intronless Pre-mRNA - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put("Processing of Capped pre-mRNA - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Processing of DNA double-strand break ends - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put(
				"Processing of DNA ends prior to end rejoining - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Processing of Intronless Pre-mRNAs - (Reactome via Pathway Interaction Database)", "28");
		pgkbPathwayCount.put(
				"Processive synthesis on the C-strand of the telomere - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put("Processive synthesis on the lagging strand - (Reactome via Pathway Interaction Database)",
				"20");
		pgkbPathwayCount.put("Proline catabolism - (Reactome via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put("Proline synthesis - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("Prolonged ERK activation events - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Propionyl-CoA catabolism - (Reactome via Pathway Interaction Database)", "15");
		pgkbPathwayCount.put("Prostanoid hormones - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("proteasome complex - (BioCarta via Pathway Interaction Database)", "43");
		pgkbPathwayCount.put("Protein folding - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("protein kinase a at the centrosome - (BioCarta via Pathway Interaction Database)", "40");
		pgkbPathwayCount.put(
				"Proteinase activated receptor G alpha (12/13) cascade - (Reactome via Pathway Interaction Database)",
				"9");
		pgkbPathwayCount.put(
				"Proteinase activated receptor G alpha (q) cascade - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put(
				"Proteogylcan syndecan-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"5");
		pgkbPathwayCount.put("proteolysis and signaling pathway of notch - (BioCarta via Pathway Interaction Database)",
				"26");
		pgkbPathwayCount.put(
				"Proteolytic cleavage of SNARE complex proteins - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Proton Pump Inhibitor Pathway", "119");
		pgkbPathwayCount.put(
				"pten dependent cell cycle arrest and apoptosis - (BioCarta via Pathway Interaction Database)", "37");
		pgkbPathwayCount.put("Purine biosynthesis - (Reactome via Pathway Interaction Database)", "29");
		pgkbPathwayCount.put("Purine catabolism - (Reactome via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put("Purine metabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Purine salvage reactions - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Pyrimidine biosynthesis (interconversion) - (Reactome via Pathway Interaction Database)",
				"27");
		pgkbPathwayCount.put("Pyrimidine catabolism - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Pyrimidine metabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Pyrimidine salvage reactions - (Reactome via Pathway Interaction Database)", "11");
		pgkbPathwayCount.put("Pyruvate metabolism - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Pyruvate metabolism and TCA cycle - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("rac1 cell motility signaling pathway - (BioCarta via Pathway Interaction Database)",
				"36");
		pgkbPathwayCount.put("RAF activation - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("RAF phosphorylates MEK - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount
				.put("Ras signaling in the CD4+ TCR pathway - (Pathway Interaction Database NCI-Nature Curated)", "53");
		pgkbPathwayCount.put("ras signaling pathway - (BioCarta via Pathway Interaction Database)", "46");
		pgkbPathwayCount.put(
				"ras-independent pathway in nk cell-mediated cytotoxicity - (BioCarta via Pathway Interaction Database)",
				"92");
		pgkbPathwayCount.put(
				"rb tumor suppressor/checkpoint signaling in response to dna damage - (BioCarta via Pathway Interaction Database)",
				"37");
		pgkbPathwayCount.put(
				"Receptor-ligand binding initiates the second proteolytic cleavage of Notch receptor - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put("Receptor-ligand complexes bind G proteins - (Reactome via Pathway Interaction Database)",
				"15");
		pgkbPathwayCount.put(
				"Recognition and association of DNA glycosylase with site containing an affected purine - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put(
				"Recognition and association of DNA glycosylase with site containing an affected pyrimidine - (Reactome via Pathway Interaction Database)",
				"12");
		pgkbPathwayCount.put(
				"Recruitment of mitotic centrosome proteins and complexes - (Reactome via Pathway Interaction Database)",
				"140");
		pgkbPathwayCount.put("Recruitment of NuMA to mitotic centrosomes - (Reactome via Pathway Interaction Database)",
				"20");
		pgkbPathwayCount.put(
				"Recruitment of repair and signaling proteins to double-strand breaks - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put("Recycling of bile acids and salts - (Reactome via Pathway Interaction Database)", "36");
		pgkbPathwayCount.put("Recycling of eIF2:GDP - (Reactome via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put(
				"Reduction of cytosolic ribonucleoside 5'-diphosphates to deoxyribonucleoside 5'-diphosphates (glutaredoxin) - (Reactome via Pathway Interaction Database)",
				"17");
		pgkbPathwayCount.put(
				"Reduction of cytosolic ribonucleoside 5'-diphosphates to deoxyribonucleoside 5'-diphosphates (thioredoxin) - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put(
				"Reduction of cytosolic ribonucleoside 5'-diphosphates to deoxyribonucleoside 5'-diphosphates by ribonucleotide reductase and glutaredoxin - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put(
				"Reduction of cytosolic ribonucleoside 5'-diphosphates to deoxyribonucleoside 5'-diphosphates by ribonucleotide reductase and thioredoxin - (Reactome via Pathway Interaction Database)",
				"5");
		pgkbPathwayCount.put(
				"Reduction of nuclear ribonucleoside 5'-diphosphates to deoxyribonucleoside 5'-diphosphates (thioredoxin) - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put(
				"Reduction of nuclear ribonucleoside 5'-diphosphates to deoxyribonucleoside 5'-diphosphates by ribonucleotide reductase and thioredoxin - (Reactome via Pathway Interaction Database)",
				"5");
		pgkbPathwayCount.put("Reelin signaling pathway - (Pathway Interaction Database NCI-Nature Curated)", "93");
		pgkbPathwayCount.put("Regulated proteolysis of p75NTR - (Reactome via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put(
				"Regulation of activated PAK-2p34 by proteasome mediated degradation - (Reactome via Pathway Interaction Database)",
				"162");
		pgkbPathwayCount.put(
				"Regulation of Androgen receptor activity - (Pathway Interaction Database NCI-Nature Curated)", "135");
		pgkbPathwayCount.put(
				"Regulation of APC/C activators between G1/S and early anaphase - (Reactome via Pathway Interaction Database)",
				"40");
		pgkbPathwayCount.put("Regulation of Apoptosis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("regulation of bad phosphorylation - (BioCarta via Pathway Interaction Database)", "47");
		pgkbPathwayCount.put("Regulation of beta-cell development - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"regulation of cell cycle progression by plk3 - (BioCarta via Pathway Interaction Database)", "39");
		pgkbPathwayCount.put(
				"regulation of ck1/cdk5 by type 1 glutamate receptors - (BioCarta via Pathway Interaction Database)",
				"85");
		pgkbPathwayCount.put(
				"Regulation of cytoplasmic and nuclear SMAD2/3 signaling - (Pathway Interaction Database NCI-Nature Curated)",
				"22");
		pgkbPathwayCount.put("Regulation of DNA replication - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("regulation of eif-4e and p70s6 kinase - (BioCarta via Pathway Interaction Database)",
				"87");
		pgkbPathwayCount.put("regulation of eif2 - (BioCarta via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put("Regulation of gap junction activity - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount
				.put("Regulation of gene expression in beta cells - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount
				.put("Regulation of glucocorticoid receptor - (Pathway Interaction Database NCI-Nature Curated)", "69");
		pgkbPathwayCount.put("Regulation of IGF Activity by IGFBP - (Reactome via Pathway Interaction Database)", "23");
		pgkbPathwayCount.put(
				"regulation of map kinase pathways through dual specificity phosphatases - (BioCarta via Pathway Interaction Database)",
				"10");
		pgkbPathwayCount.put(
				"Regulation of nuclear SMAD2/3 signaling - (Pathway Interaction Database NCI-Nature Curated)", "190");
		pgkbPathwayCount
				.put("Regulation of ornithine decarboxylase (ODC) - (Reactome via Pathway Interaction Database)", "77");
		pgkbPathwayCount.put(
				"regulation of p27 phosphorylation during cell cycle progression - (BioCarta via Pathway Interaction Database)",
				"67");
		pgkbPathwayCount.put("Regulation of p38-alpha and p38-beta - (Pathway Interaction Database NCI-Nature Curated)",
				"48");
		pgkbPathwayCount.put(
				"Regulation of PAK-2p34 activity by PS-GAP/RHG10 - (Reactome via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put("regulation of pgc-1a - (BioCarta via Pathway Interaction Database)", "26");
		pgkbPathwayCount.put(
				"Regulation of pyruvate dehydrogenase complex (PDC) - (Reactome via Pathway Interaction Database)",
				"20");
		pgkbPathwayCount.put("Regulation of retinoblastoma protein - (Pathway Interaction Database NCI-Nature Curated)",
				"106");
		pgkbPathwayCount.put("regulation of spermatogenesis by crem - (BioCarta via Pathway Interaction Database)",
				"67");
		pgkbPathwayCount.put("regulation of splicing through sam68 - (BioCarta via Pathway Interaction Database)",
				"27");
		pgkbPathwayCount.put("Regulation of Telomerase - (Pathway Interaction Database NCI-Nature Curated)", "158");
		pgkbPathwayCount.put("Regulation of thyroid hormone activity - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put(
				"regulation of transcriptional activity by pml - (BioCarta via Pathway Interaction Database)", "25");
		pgkbPathwayCount.put("regulators of bone mineralization - (BioCarta via Pathway Interaction Database)", "27");
		pgkbPathwayCount.put("Regulatory RNA pathways - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Release - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Release of apoptotic factors from the mitochondria - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount.put("Release of eIF4E - (Reactome via Pathway Interaction Database)", "26");
		pgkbPathwayCount.put(
				"Removal of aminoterminal propeptides from gamma-carboxylated proteins - (Reactome via Pathway Interaction Database)",
				"67");
		pgkbPathwayCount.put(
				"Removal of DNA patch containing abasic residue - (Reactome via Pathway Interaction Database)", "26");
		pgkbPathwayCount.put("Removal of licensing factors from origins - (Reactome via Pathway Interaction Database)",
				"35");
		pgkbPathwayCount.put("Removal of the Flap Intermediate - (Reactome via Pathway Interaction Database)", "39");
		pgkbPathwayCount.put(
				"Removal of the Flap Intermediate from the C-strand - (Reactome via Pathway Interaction Database)",
				"33");
		pgkbPathwayCount.put(
				"Repair synthesis for gap-filling by DNA polymerase in TC-NER - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put(
				"Repair synthesis of patch ~27-30 bases long  by DNA polymerase - (Reactome via Pathway Interaction Database)",
				"12");
		pgkbPathwayCount.put(
				"repression of pain sensation by the transcriptional regulator dream - (BioCarta via Pathway Interaction Database)",
				"48");
		pgkbPathwayCount.put("Resolution of Abasic Sites (AP sites) - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Resolution of AP sites via the multiple-nucleotide patch replacement pathway - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put(
				"Resolution of AP sites via the single-nucleotide replacement pathway - (Reactome via Pathway Interaction Database)",
				"5");
		pgkbPathwayCount.put("Resolution of D-loop structures - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put(
				"Resolution of D-loop structures through Holliday junction intermediates - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put(
				"Resolution of D-loop structures through synthesis-dependent strand-annealing - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount
				.put("Response to elevated platelet cytosolic Ca++ - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Retinoic acid receptors-mediated signaling - (Pathway Interaction Database NCI-Nature Curated)", "64");
		pgkbPathwayCount.put("Retrograde neurotrophin signalling - (Reactome via Pathway Interaction Database)", "25");
		pgkbPathwayCount.put("Rev-mediated nuclear export of HIV-1 RNA - (Reactome via Pathway Interaction Database)",
				"38");
		pgkbPathwayCount.put(
				"Reversal of Alkylation Damage By DNA Dioxygenases - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("reversal of insulin resistance by leptin - (BioCarta via Pathway Interaction Database)",
				"15");
		pgkbPathwayCount.put("Reverse Transcription of HIV RNA - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Reversible phosphorolysis of pyrimidine nucleosides - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Reversible phosphorolysis of pyrimidine nucleosides by thymidine phosphorylase - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount.put(
				"Reversible phosphorolysis of pyrimidine nucleosides by uridine phosphorylase 1 - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of cytosolic nucleoside diphosphates by nucleoside diphosphate kinase A",
				"13");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of cytosolic nucleoside diphosphates by nucleoside diphosphate kinase A hexamer - (Reactome via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of cytosolic nucleoside diphosphates by nucleoside diphosphate kinase B hexamer - (Reactome via Pathway Interaction Database)",
				"12");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of cytosolic nucleoside diphosphates by nucleoside diphosphate kinase C hexamer - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of cytosolic nucleoside monophosphates by adenylate kinase 1 - (Reactome via Pathway Interaction Database)",
				"12");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of cytosolic nucleoside monophosphates by adenylate kinase 5 - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of cytosolic nucleoside monophosphates by deoxythymidylate kinase (thymidylate kinase) - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of cytosolic nucleoside monophosphates by guanylate kinase 1 - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of cytosolic nucleoside monophosphates by UMP-CMP kinase - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of mitochondrial nucleoside diphosphates by nucleoside diphosphate kinase D",
				"6");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of mitochondrial nucleoside monophosphates by adenylate kinase 2 - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of nucleoside diphosphates - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Reversible phosphorylation of nucleoside monophosphates - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("rho cell motility signaling pathway - (BioCarta via Pathway Interaction Database)", "29");
		pgkbPathwayCount.put("Rho GTPase cycle - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put(
				"rho-selective guanine exchange factor akap13 mediates stress fiber formation - (BioCarta via Pathway Interaction Database)",
				"21");
		pgkbPathwayCount.put("Ribonucleotide salvage - (Reactome via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put(
				"Ribosomal scanning and start codon recognition - (Reactome via Pathway Interaction Database)", "130");
		pgkbPathwayCount.put(
				"RNA Pol II CTD phosphorylation and interaction with CE - (Reactome via Pathway Interaction Database)",
				"110");
		pgkbPathwayCount.put("RNA Polymerase I Chain Elongation - (Reactome via Pathway Interaction Database)", "39");
		pgkbPathwayCount.put("RNA Polymerase I Promoter Clearance - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("RNA Polymerase I Promoter Escape - (Reactome via Pathway Interaction Database)", "41");
		pgkbPathwayCount.put("RNA Polymerase I Promoter Opening - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("RNA Polymerase I Transcription - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("RNA Polymerase I Transcription Initiation - (Reactome via Pathway Interaction Database)",
				"41");
		pgkbPathwayCount.put("RNA Polymerase I Transcription Termination - (Reactome via Pathway Interaction Database)",
				"41");
		pgkbPathwayCount.put("RNA Polymerase II HIV-1 Promoter Escape - (Reactome via Pathway Interaction Database)",
				"70");
		pgkbPathwayCount.put("RNA Polymerase II Promoter Escape - (Reactome via Pathway Interaction Database)", "70");
		pgkbPathwayCount.put("RNA Polymerase II Transcription - (Reactome via Pathway Interaction Database)", "70");
		pgkbPathwayCount.put("RNA Polymerase II Transcription Elongation - (Reactome via Pathway Interaction Database)",
				"98");
		pgkbPathwayCount.put("RNA Polymerase II Transcription Initiation - (Reactome via Pathway Interaction Database)",
				"70");
		pgkbPathwayCount.put(
				"RNA Polymerase II Transcription Initiation And Promoter Clearance - (Reactome via Pathway Interaction Database)",
				"70");
		pgkbPathwayCount.put(
				"RNA Polymerase II Transcription Pre-Initiation - (Reactome via Pathway Interaction Database)", "70");
		pgkbPathwayCount
				.put("RNA Polymerase II Transcription Termination - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("RNA Polymerase III Chain Elongation - (Reactome via Pathway Interaction Database)", "53");
		pgkbPathwayCount.put("rna polymerase iii transcription - (BioCarta via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("RNA Polymerase III Transcription - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount
				.put("RNA Polymerase III Transcription Initiation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"RNA Polymerase III Transcription Initiation From Type 1 Promoter - (Reactome via Pathway Interaction Database)",
				"62");
		pgkbPathwayCount.put(
				"RNA Polymerase III Transcription Initiation From Type 2 Promoter - (Reactome via Pathway Interaction Database)",
				"61");
		pgkbPathwayCount.put(
				"RNA Polymerase III Transcription Initiation From Type 3 Promoter - (Reactome via Pathway Interaction Database)",
				"65");
		pgkbPathwayCount.put(
				"RNA Polymerase III Transcription Termination - (Reactome via Pathway Interaction Database)", "53");
		pgkbPathwayCount.put(
				"role of brca1 brca2 and atr in cancer susceptibility - (BioCarta via Pathway Interaction Database)",
				"138");
		pgkbPathwayCount.put(
				"Role of Calcineurin-dependent NFAT signaling in lymphocytes - (Pathway Interaction Database NCI-Nature Curated)",
				"69");
		pgkbPathwayCount.put(
				"role of egf receptor transactivation by gpcrs in cardiac hypertrophy - (BioCarta via Pathway Interaction Database)",
				"90");
		pgkbPathwayCount.put(
				"role of erbb2 in signal transduction and oncology - (BioCarta via Pathway Interaction Database)",
				"55");
		pgkbPathwayCount.put("role of erk5 in neuronal survival pathway - (BioCarta via Pathway Interaction Database)",
				"48");
		pgkbPathwayCount.put(
				"role of mal in rho-mediated activation of srf - (BioCarta via Pathway Interaction Database)", "74");
		pgkbPathwayCount.put("role of mef2d in t-cell apoptosis - (BioCarta via Pathway Interaction Database)", "107");
		pgkbPathwayCount
				.put("role of mitochondria in apoptotic signaling - (BioCarta via Pathway Interaction Database)", "22");
		pgkbPathwayCount.put(
				"role of nicotinic acetylcholine receptors in the regulation of apoptosis - (BioCarta via Pathway Interaction Database)",
				"33");
		pgkbPathwayCount.put(
				"role of parkin in ubiquitin-proteasomal pathway - (BioCarta via Pathway Interaction Database)", "28");
		pgkbPathwayCount.put(
				"role of pi3k subunit p85 in regulation of actin organization and cell migration - (BioCarta via Pathway Interaction Database)",
				"28");
		pgkbPathwayCount.put(
				"role of ppar-gamma coactivators in obesity and thermogenesis - (BioCarta via Pathway Interaction Database)",
				"32");
		pgkbPathwayCount.put("role of ran in mitotic spindle regulation - (BioCarta via Pathway Interaction Database)",
				"19");
		pgkbPathwayCount.put(
				"role of ß-arrestins in the activation and targeting of map kinases - (BioCarta via Pathway Interaction Database)",
				"48");
		pgkbPathwayCount.put(
				"roles of ß arrestin dependent recruitment of src kinases in gpcr signaling - (BioCarta via Pathway Interaction Database)",
				"77");
		pgkbPathwayCount.put("Rosiglitazone Pharmacokinetic Pathway", "5");
		pgkbPathwayCount.put("Rosuvastatin Pathway", "13");
		pgkbPathwayCount.put(
				"RXR and RAR heterodimerization with other nuclear receptor - (Pathway Interaction Database NCI-Nature Curated)",
				"50");
		pgkbPathwayCount.put("S Phase - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("S1P1 pathway - (Pathway Interaction Database NCI-Nature Curated)", "35");
		pgkbPathwayCount.put("S1P2 pathway - (Pathway Interaction Database NCI-Nature Curated)", "67");
		pgkbPathwayCount.put("S1P3 pathway - (Pathway Interaction Database NCI-Nature Curated)", "47");
		pgkbPathwayCount.put("S1P4 pathway - (Pathway Interaction Database NCI-Nature Curated)", "22");
		pgkbPathwayCount.put("S1P5 pathway - (Pathway Interaction Database NCI-Nature Curated)", "14");
		pgkbPathwayCount.put("S6K1 signalling - (Reactome via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put("S6K1-mediated signalling - (Reactome via Pathway Interaction Database)", "24");
		pgkbPathwayCount.put("SCF(Skp2)-mediated degradation of p27/p21 - (Reactome via Pathway Interaction Database)",
				"82");
		pgkbPathwayCount.put("SCF-beta-TrCP mediated degradation of Emi1 - (Reactome via Pathway Interaction Database)",
				"86");
		pgkbPathwayCount.put("segmentation clock - (BioCarta via Pathway Interaction Database)", "42");
		pgkbPathwayCount.put("Selective Serotonin Reuptake Inhibitor Pathway", "94");
		pgkbPathwayCount.put("Serotonin and melatonin biosynthesis - (Reactome via Pathway Interaction Database)",
				"10");
		pgkbPathwayCount
				.put("Serotonin clearance from the synaptic cleft - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Serotonin Neurotransmitter Release Cycle - (Reactome via Pathway Interaction Database)",
				"16");
		pgkbPathwayCount.put("Serotonin receptors - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("SHC activation - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put("Shc events in EGFR signaling - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("SHC-mediated signalling - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("SHC-related events - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Signal attenuation - (Reactome via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put(
				"signal dependent regulation of myogenesis by corepressor mitr - (BioCarta via Pathway Interaction Database)",
				"15");
		pgkbPathwayCount.put("signal transduction through il1r - (BioCarta via Pathway Interaction Database)", "64");
		pgkbPathwayCount.put("Signaling  by TGF beta - (Reactome via Pathway Interaction Database)", "27");
		pgkbPathwayCount.put("Signaling by Aurora kinases - (Pathway Interaction Database NCI-Nature Curated)", "1");
		pgkbPathwayCount.put("Signaling by BMP - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Signaling by EGFR - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("Signaling by FGFR - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Signaling by GPCR - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Signaling by Insulin receptor - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("Signaling by Notch - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Signaling by PDGF - (Reactome via Pathway Interaction Database)", "25");
		pgkbPathwayCount.put("Signaling by Rho GTPases - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Signaling by TGF beta - (Reactome via Pathway Interaction Database)", "22");
		pgkbPathwayCount.put("Signaling by VEGF - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Signaling by Wnt - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Signaling events activated by Hepatocyte Growth Factor Receptor (c-Met) - (Pathway Interaction Database NCI-Nature Curated)",
				"127");
		pgkbPathwayCount.put(
				"Signaling events mediated by HDAC Class I - (Pathway Interaction Database NCI-Nature Curated)", "104");
		pgkbPathwayCount.put(
				"Signaling events mediated by HDAC Class II - (Pathway Interaction Database NCI-Nature Curated)",
				"104");
		pgkbPathwayCount.put(
				"Signaling events mediated by HDAC Class III - (Pathway Interaction Database NCI-Nature Curated)",
				"84");
		pgkbPathwayCount.put("Signaling events mediated by PRL - (Pathway Interaction Database NCI-Nature Curated)",
				"52");
		pgkbPathwayCount.put("Signaling events mediated by PTP1B - (Pathway Interaction Database NCI-Nature Curated)",
				"108");
		pgkbPathwayCount.put(
				"Signaling events mediated by Stem cell factor receptor (c-Kit) - (Pathway Interaction Database NCI-Nature Curated)",
				"111");
		pgkbPathwayCount.put(
				"Signaling events mediated by the Hedgehog family - (Pathway Interaction Database NCI-Nature Curated)",
				"56");
		pgkbPathwayCount.put(
				"Signaling events mediated by VEGFR1 and VEGFR2 - (Pathway Interaction Database NCI-Nature Curated)",
				"156");
		pgkbPathwayCount.put(
				"Signaling events regulated by Ret tyrosine kinase - (Pathway Interaction Database NCI-Nature Curated)",
				"82");
		pgkbPathwayCount.put("Signaling in Immune system - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Signaling mediated by p38-alpha and p38-beta - (Pathway Interaction Database NCI-Nature Curated)",
				"70");
		pgkbPathwayCount.put(
				"Signaling mediated by p38-gamma and p38-delta - (Pathway Interaction Database NCI-Nature Curated)",
				"25");
		pgkbPathwayCount.put("signaling pathway from g-protein families - (BioCarta via Pathway Interaction Database)",
				"94");
		pgkbPathwayCount.put("Signalling by NGF - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Signalling to ERK5 - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Signalling to ERKs - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Signalling to p38 via RIT and RIN - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("Signalling to RAS - (Reactome via Pathway Interaction Database)", "11");
		pgkbPathwayCount.put("Signalling to STAT3 - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put("Simple hydroxylation - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put(
				"skeletal muscle hypertrophy is regulated via akt-mtor pathway - (BioCarta via Pathway Interaction Database)",
				"66");
		pgkbPathwayCount.put(
				"SLBP Dependent Processing of Replication-Dependent Histone Pre-mRNAs - (Reactome via Pathway Interaction Database)",
				"35");
		pgkbPathwayCount.put(
				"SLBP independent Processing of Histone Pre-mRNAs - (Reactome via Pathway Interaction Database)", "35");
		pgkbPathwayCount.put("SMAC binds to IAPs - (Reactome via Pathway Interaction Database)", "11");
		pgkbPathwayCount.put("SMAC-mediated apoptotic response - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"SMAC-mediated dissociation of IAP:caspase complexes - (Reactome via Pathway Interaction Database)",
				"11");
		pgkbPathwayCount.put("snRNP Assembly - (Reactome via Pathway Interaction Database)", "64");
		pgkbPathwayCount.put("sodd/tnfr1 signaling pathway - (BioCarta via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put(
				"sonic hedgehog receptor ptc1 regulates cell cycle - (BioCarta via Pathway Interaction Database)",
				"19");
		pgkbPathwayCount.put("Sorafenib Pharmacodynamics", "96");
		pgkbPathwayCount.put("Sorafenib Pharmacokinetics", "17");
		pgkbPathwayCount.put("SOS-mediated signalling - (Reactome via Pathway Interaction Database)", "7");
		pgkbPathwayCount
				.put("Sphingosine 1-phosphate (S1P) pathway - (Pathway Interaction Database NCI-Nature Curated)", "30");
		pgkbPathwayCount.put("spliceosomal assembly - (BioCarta via Pathway Interaction Database)", "26");
		pgkbPathwayCount.put(
				"sprouty regulation of tyrosine kinase signals - (BioCarta via Pathway Interaction Database)", "35");
		pgkbPathwayCount.put("srebp control of lipid synthesis - (BioCarta via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put(
				"Stabilization and expansion of the E-cadherin adherens junction - (Pathway Interaction Database NCI-Nature Curated)",
				"54");
		pgkbPathwayCount.put("Stabilization of p53 - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("stat3 signaling pathway - (BioCarta via Pathway Interaction Database)", "27");
		pgkbPathwayCount.put(
				"stathmin and breast cancer resistance to antimicrotubule agents - (BioCarta via Pathway Interaction Database)",
				"32");
		pgkbPathwayCount.put("Statin Pathway", "78");
		pgkbPathwayCount.put("Statin Pathway - Generalized", "50");
		pgkbPathwayCount.put("Steroid hormone biosynthesis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Steroid hormones - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Steroid metabolism - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Sterols are 12-hydroxylated by CYP8B1 - (Reactome via Pathway Interaction Database)",
				"7");
		pgkbPathwayCount.put(
				"Stimulation of the cell death response by PAK-2p34 - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put("stress induction of hsp regulation - (BioCarta via Pathway Interaction Database)", "28");
		pgkbPathwayCount.put("Striated Muscle Contraction - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put(
				"sumoylation as a mechanism to modulate ctbp-dependent gene responses - (BioCarta via Pathway Interaction Database)",
				"27");
		pgkbPathwayCount.put(
				"sumoylation by ranbp2 regulates transcriptional repression - (BioCarta via Pathway Interaction Database)",
				"30");
		pgkbPathwayCount.put(
				"Sumoylation by RanBP2 regulates transcriptional repression - (Pathway Interaction Database NCI-Nature Curated)",
				"32");
		pgkbPathwayCount.put(
				"Switching of origins to a post-replicative state - (Reactome via Pathway Interaction Database)", "14");
		pgkbPathwayCount.put("Sympathetic Nerve Pathway (Neuroeffector Junction)", "91");
		pgkbPathwayCount.put("Sympathetic Nerve Pathway (Pre- and Post- Ganglionic Junction)", "17");
		pgkbPathwayCount.put("Synaptic Transmission - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Syndecan-1-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"36");
		pgkbPathwayCount.put("Syndecan-2-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"81");
		pgkbPathwayCount.put("Syndecan-3-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"53");
		pgkbPathwayCount.put("Syndecan-4-mediated signaling events - (Pathway Interaction Database NCI-Nature Curated)",
				"105");
		pgkbPathwayCount.put("Synthesis of bile acids and bile salts - (Reactome via Pathway Interaction Database)",
				"8");
		pgkbPathwayCount.put(
				"Synthesis of bile acids and bile salts via 24-hydroxycholesterol - (Reactome via Pathway Interaction Database)",
				"20");
		pgkbPathwayCount.put(
				"Synthesis of bile acids and bile salts via 27-hydroxycholesterol - (Reactome via Pathway Interaction Database)",
				"16");
		pgkbPathwayCount.put(
				"Synthesis of bile acids and bile salts via 7alpha-hydroxycholesterol - (Reactome via Pathway Interaction Database)",
				"32");
		pgkbPathwayCount.put(
				"Synthesis of deoxyribonucleoside 5'-diphosphates from ribonucleoside 5'-diphosphates - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Synthesis of DNA - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Synthesis of dolichol-phosphate mannose - (Reactome via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put("Synthesis of Dopamine - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put(
				"Synthesis of glycosylphosphatidylinositol (GPI) - (Reactome via Pathway Interaction Database)", "80");
		pgkbPathwayCount.put("Synthesis of GPI-anchored proteins - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("Synthesis of Ketone Bodies - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put(
				"Synthesis of lysophosphatidic acid from dihydroxyacetone phosphate - (Reactome via Pathway Interaction Database)",
				"13");
		pgkbPathwayCount.put(
				"Synthesis of lysophosphatidic acid from glycerol-3-phosphate - (Reactome via Pathway Interaction Database)",
				"3");
		pgkbPathwayCount.put(
				"Synthesis of phosphatidic acid from lysophosphatidic acid - (Reactome via Pathway Interaction Database)",
				"11");
		pgkbPathwayCount.put("Synthesis of Serotonin - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("t cell receptor signaling pathway - (BioCarta via Pathway Interaction Database)", "158");
		pgkbPathwayCount.put("Tachykinin receptors bind tachykinins - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put("Tamoxifen Pathway", "57");
		pgkbPathwayCount.put(
				"Tat-mediated elongation of the HIV-1 transcript - (Reactome via Pathway Interaction Database)", "96");
		pgkbPathwayCount.put(
				"Tat-mediated HIV-1 elongation arrest and recovery - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Taxane Pathway", "36");
		pgkbPathwayCount.put("TCR signaling - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("TCR signaling in naïve CD4+ T cells - (Pathway Interaction Database NCI-Nature Curated)",
				"172");
		pgkbPathwayCount.put("TCR signaling in naïve CD8+ T cells - (Pathway Interaction Database NCI-Nature Curated)",
				"156");
		pgkbPathwayCount.put(
				"Telomere C-strand (Lagging Strand) Synthesis - (Reactome via Pathway Interaction Database)", "14");
		pgkbPathwayCount.put("Telomere C-strand synthesis initiation - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put("Telomere Extension By Telomerase - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Telomere Maintenance - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"telomeres telomerase cellular aging and immortality - (BioCarta via Pathway Interaction Database)",
				"35");
		pgkbPathwayCount.put("Tenofovir/Adefovir Pathway", "34");
		pgkbPathwayCount.put("Terminal pathway of complement - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("tgf beta signaling pathway - (BioCarta via Pathway Interaction Database)", "33");
		pgkbPathwayCount.put("TGF-beta receptor signaling - (Pathway Interaction Database NCI-Nature Curated)", "84");
		pgkbPathwayCount.put("the 41bb-dependent immune response - (BioCarta via Pathway Interaction Database)", "28");
		pgkbPathwayCount.put(
				"the co-stimulatory signal during t-cell activation - (BioCarta via Pathway Interaction Database)",
				"74");
		pgkbPathwayCount.put("The fatty acid cycling model - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("the igf-1 receptor and longevity - (BioCarta via Pathway Interaction Database)", "35");
		pgkbPathwayCount.put(
				"the information processing pathway at the ifn beta enhancer - (BioCarta via Pathway Interaction Database)",
				"49");
		pgkbPathwayCount.put(
				"the prc2 complex sets long-term gene silencing through modification of histone tails - (BioCarta via Pathway Interaction Database)",
				"31");
		pgkbPathwayCount.put("The proton buffering model - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"The role of Nef in HIV-1 replication and disease pathogenesis - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Theophylline Pathway", "21");
		pgkbPathwayCount.put("Thiopurine Pathway", "93");
		pgkbPathwayCount.put(
				"thrombin signaling and protease-activated receptors - (BioCarta via Pathway Interaction Database)",
				"55");
		pgkbPathwayCount.put("Thrombin signalling G-protein cascades - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Thrombin signalling through PARs - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Thrombin-activated activation cascade - (Reactome via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put("Thrombin-mediated activation of PARs - (Reactome via Pathway Interaction Database)",
				"11");
		pgkbPathwayCount.put("Thromboxane A2 receptor signaling - (Pathway Interaction Database NCI-Nature Curated)",
				"1241");
		pgkbPathwayCount.put("Thyroxine biosynthesis - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("Tie2 Signaling - (Reactome via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put("TNF receptor signaling pathway - (Pathway Interaction Database NCI-Nature Curated)",
				"77");
		pgkbPathwayCount.put("TNF signaling - (Reactome via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put("tnf/stress related signaling - (BioCarta via Pathway Interaction Database)", "52");
		pgkbPathwayCount.put("tnfr1 signaling pathway - (BioCarta via Pathway Interaction Database)", "40");
		pgkbPathwayCount.put("tnfr2 signaling pathway - (BioCarta via Pathway Interaction Database)", "29");
		pgkbPathwayCount.put("Toll Like Receptor 10 (TLR10) Cascade - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Toll Like Receptor 2 Cascade - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Toll Like Receptor 3 (TLR3) Cascade - (Reactome via Pathway Interaction Database)", "9");
		pgkbPathwayCount.put("Toll Like Receptor 4 (TLR4) Cascade - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put("Toll Like Receptor 5 (TLR5) Cascade - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Toll Like Receptor 7/8 (TLR7/8) Cascade - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Toll Like Receptor 9 (TLR9) Cascade - (Reactome via Pathway Interaction Database)", "5");
		pgkbPathwayCount.put("Toll Like Receptor TLR1:TLR2 Cascade - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Toll Like Receptor TLR6:TLR2 Cascade - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Toll Receptor Cascades - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("toll-like receptor pathway - (BioCarta via Pathway Interaction Database)", "86");
		pgkbPathwayCount.put("tpo signaling pathway - (BioCarta via Pathway Interaction Database)", "72");
		pgkbPathwayCount.put(
				"TRAF6 Mediated Induction of the antiviral cytokine IFN-alpha/beta cascade - (Reactome via Pathway Interaction Database)",
				"27");
		pgkbPathwayCount.put("Trafficking of dietary sterols - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put("TRAIL  signaling - (Reactome via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put("TRAIL signaling pathway - (Pathway Interaction Database NCI-Nature Curated)", "48");
		pgkbPathwayCount.put("TRAM Cascade - (Reactome via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put("Tramadol Pharmacokinetics", "20");
		pgkbPathwayCount.put("trans-Golgi Network Vesicle Budding - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Transcription - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"transcription factor creb and its extracellular signals - (BioCarta via Pathway Interaction Database)",
				"66");
		pgkbPathwayCount.put("Transcription from mitochondrial promoters - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Transcription of the HIV genome - (Reactome via Pathway Interaction Database)", "70");
		pgkbPathwayCount.put(
				"transcription regulation by methyltransferase of carm1 - (BioCarta via Pathway Interaction Database)",
				"39");
		pgkbPathwayCount.put("Transcription-coupled NER (TC-NER) - (Reactome via Pathway Interaction Database)", "38");
		pgkbPathwayCount.put(
				"transcriptional activation of dbpb from mrna - (BioCarta via Pathway Interaction Database)", "16");
		pgkbPathwayCount
				.put("Transformation of lanosterol to cholesterol - (Reactome via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put("Translation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Translation initiation complex formation - (Reactome via Pathway Interaction Database)",
				"130");
		pgkbPathwayCount.put(
				"Translesion synthesis by DNA polymerases bypassing lesion on DNA template - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Translesion synthesis by HREV1 - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Translesion synthesis by Pol eta - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Translesion synthesis by Pol zeta - (Reactome via Pathway Interaction Database)", "2");
		pgkbPathwayCount.put("Translocation of BoNT Light chain - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Translocation of ZAP-70 to Immunological synapse - (Reactome via Pathway Interaction Database)", "10");
		pgkbPathwayCount.put("Transmembrane transport of small molecules - (Reactome via Pathway Interaction Database)",
				"6");
		pgkbPathwayCount.put("Transmission across Chemical Synapses - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Transmission across Electrical Synapses - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Transport of connexins along the secretory pathway - (Reactome via Pathway Interaction Database)",
				"5");
		pgkbPathwayCount.put(
				"Transport of connexons to the plasma membrane - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put(
				"Transport of gamma-carboxylated protein precursors from the endoplasmic reticulum to the Golgi apparatus - (Reactome via Pathway Interaction Database)",
				"55");
		pgkbPathwayCount.put("Transport of HA trimer", "2");
		pgkbPathwayCount.put(
				"Transport of Mature mRNA derived from an Intron-Containing Transcript - (Reactome via Pathway Interaction Database)",
				"50");
		pgkbPathwayCount.put(
				"Transport of Mature mRNA Derived from an Intronless Transcript - (Reactome via Pathway Interaction Database)",
				"14");
		pgkbPathwayCount.put(
				"Transport of Mature mRNAs Derived from Intronless Transcripts - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount
				.put("Transport of Mature Transcript to Cytoplasm - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"Transport of Notch receptor precursor to golgi - (Reactome via Pathway Interaction Database)", "6");
		pgkbPathwayCount.put(
				"Transport of nucleosides and free purine and pyrimidine bases across the plasma membrane - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put(
				"Transport of Ribonucleoproteins into the Host Nucleus - (Reactome via Pathway Interaction Database)",
				"4");
		pgkbPathwayCount
				.put("Transport of the SLBP Dependant Mature mRNA - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put(
				"Transport of the SLBP independent Mature mRNA - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("trefoil factors initiate mucosal healing - (BioCarta via Pathway Interaction Database)",
				"89");
		pgkbPathwayCount.put("Triacylglyceride Biosynthesis - (Reactome via Pathway Interaction Database)", "4");
		pgkbPathwayCount.put(
				"Trk receptor signaling mediated by PI3K and PLC-gamma - (Pathway Interaction Database NCI-Nature Curated)",
				"60");
		pgkbPathwayCount.put(
				"Trk receptor signaling mediated by the MAPK pathway - (Pathway Interaction Database NCI-Nature Curated)",
				"78");
		pgkbPathwayCount.put("TRKA activation by NGF - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("trka receptor signaling pathway - (BioCarta via Pathway Interaction Database)", "35");
		pgkbPathwayCount.put("TRKA signalling from the plasma membrane - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("tRNA Aminoacylation - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Tryptophan catabolism - (Reactome via Pathway Interaction Database)", "15");
		pgkbPathwayCount.put(
				"tsp-1 induced apoptosis in microvascular endothelial cell - (BioCarta via Pathway Interaction Database)",
				"54");
		pgkbPathwayCount.put(
				"tumor suppressor arf inhibits ribosomal biogenesis - (BioCarta via Pathway Interaction Database)",
				"55");
		pgkbPathwayCount.put(
				"Ubiquitin Mediated Degradation of Phosphorylated Cdc25A - (Reactome via Pathway Interaction Database)",
				"78");
		pgkbPathwayCount
				.put("Ubiquitin-dependent degradation of Cyclin D - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put(
				"Ubiquitin-dependent degradation of Cyclin D1 - (Reactome via Pathway Interaction Database)", "81");
		pgkbPathwayCount.put("ucalpain and friends in cell spread - (BioCarta via Pathway Interaction Database)", "35");
		pgkbPathwayCount.put("Uncoating of the HIV Virion - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Uncoating of the Influenza Virion - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Unknown - (Reactome via Pathway Interaction Database)", "19");
		pgkbPathwayCount.put("Unwinding of DNA - (Reactome via Pathway Interaction Database)", "20");
		pgkbPathwayCount.put("Urea synthesis - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("Utilization of Ketone Bodies - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put(
				"UTP and glucose 1-phosphate react to form UDP-glucose and pyrophosphate - (Reactome via Pathway Interaction Database)",
				"2");
		pgkbPathwayCount.put("Valine catabolism - (Reactome via Pathway Interaction Database)", "21");
		pgkbPathwayCount.put("Valproic Acid Pathway", "111");
		pgkbPathwayCount.put(
				"Vamp2 associated Clathrin derived vesicle budding - (Reactome via Pathway Interaction Database)",
				"39");
		pgkbPathwayCount.put(
				"Vamp7 associated clathrin derived vesicle budding - (Reactome via Pathway Interaction Database)",
				"45");
		pgkbPathwayCount.put(
				"Vamp8 associated clathrin derived vesicle budding - (Reactome via Pathway Interaction Database)",
				"40");
		pgkbPathwayCount.put("Vasopressin-like receptors - (Reactome via Pathway Interaction Database)", "8");
		pgkbPathwayCount.put(
				"VEGF binds to VEGFR leading to receptor dimerization - (Reactome via Pathway Interaction Database)",
				"10");
		pgkbPathwayCount.put("vegf hypoxia and angiogenesis - (BioCarta via Pathway Interaction Database)", "68");
		pgkbPathwayCount.put("VEGF ligand-receptor interactions - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("VEGF Signaling Pathway", "149");
		pgkbPathwayCount.put("VEGFR1 specific signals - (Pathway Interaction Database NCI-Nature Curated)", "62");
		pgkbPathwayCount.put(
				"VEGFR3 signaling in lymphatic endothelium - (Pathway Interaction Database NCI-Nature Curated)", "76");
		pgkbPathwayCount.put("Vemurafenib Pathway", "134");
		pgkbPathwayCount.put("Venlafaxine Pathway", "17");
		pgkbPathwayCount.put("Vif-mediated degradation of APOBEC3G - (Reactome via Pathway Interaction Database)",
				"81");
		pgkbPathwayCount.put("Vinka Alkaloid Pathway", "27");
		pgkbPathwayCount.put(
				"Viral dsRNA:TLR3:TRIF Complex Activates RIP1 - (Reactome via Pathway Interaction Database)", "18");
		pgkbPathwayCount.put(
				"Viral dsRNA:TLR3:TRIF Complex Activates TBK1 - (Reactome via Pathway Interaction Database)", "13");
		pgkbPathwayCount.put("Viral Messenger RNA Synthesis - (Reactome via Pathway Interaction Database)", "120");
		pgkbPathwayCount.put("Viral mRNA Translation - (Reactome via Pathway Interaction Database)", "149");
		pgkbPathwayCount
				.put("Viral RNP Complexes in the Host Cell Nucleus - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("Virus Assembly and Release - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put(
				"visceral fat deposits and the metabolic syndrome - (BioCarta via Pathway Interaction Database)", "34");
		pgkbPathwayCount.put("visual signal transduction - (BioCarta via Pathway Interaction Database)", "50");
		pgkbPathwayCount.put("Visual signal transduction: Cones - (Pathway Interaction Database NCI-Nature Curated)",
				"60");
		pgkbPathwayCount.put("Visual signal transduction: Rods - (Pathway Interaction Database NCI-Nature Curated)",
				"70");
		pgkbPathwayCount.put("Vitamin B1 (thiamin) metabolism - (Reactome via Pathway Interaction Database)", "3");
		pgkbPathwayCount.put("Vitamin B2 (riboflavin) metabolism - (Reactome via Pathway Interaction Database)", "12");
		pgkbPathwayCount.put("Vitamin B5 (pantothenate) metabolism - (Reactome via Pathway Interaction Database)",
				"18");
		pgkbPathwayCount.put("Vitamin C (ascorbate) metabolism - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("Vitamin D (calciferol) metabolism - (Reactome via Pathway Interaction Database)", "29");
		pgkbPathwayCount.put("Vitamins - (Reactome via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put(
				"Vpr-mediated induction of apoptosis by mitochondrial outer membrane permeabilization - (Reactome via Pathway Interaction Database)",
				"1");
		pgkbPathwayCount.put("Vpr-mediated nuclear import of PICs - (Reactome via Pathway Interaction Database)", "42");
		pgkbPathwayCount.put("Vpu mediated degradation of CD4 - (Reactome via Pathway Interaction Database)", "86");
		pgkbPathwayCount.put("vRNA Synthesis - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("vRNP Assembly - (Reactome via Pathway Interaction Database)", "1");
		pgkbPathwayCount.put("vWF interaction with collagen - (Reactome via Pathway Interaction Database)", "36");
		pgkbPathwayCount.put("Warfarin Pathway", "107");
		pgkbPathwayCount.put("west nile virus - (BioCarta via Pathway Interaction Database)", "16");
		pgkbPathwayCount.put("wnt lrp6 signalling - (BioCarta via Pathway Interaction Database)", "7");
		pgkbPathwayCount.put("Wnt signaling - (Pathway Interaction Database NCI-Nature Curated)", "5");
		pgkbPathwayCount.put("wnt signaling pathway - (BioCarta via Pathway Interaction Database)", "62");
		pgkbPathwayCount.put("Xanthine formation - (Reactome via Pathway Interaction Database)", "14");
		pgkbPathwayCount.put("Xenobiotics - (Reactome via Pathway Interaction Database)", "47");
		pgkbPathwayCount.put("y branching of actin filaments - (BioCarta via Pathway Interaction Database)", "28");
		pgkbPathwayCount.put(
				"yaci and bcma stimulation of b cell immune responses - (BioCarta via Pathway Interaction Database)",
				"23");
		pgkbPathwayCount.put("Zidovudine Pathway", "52");
		pgkbPathwayCount.put("ß-arrestins in gpcr desensitization - (BioCarta via Pathway Interaction Database)", "44");

	} // END OF PGKB Pathway Count

	public void setpgkbDiseaseCount() {

		pgkbDiseaseCount.put("COUNT", "42636");
		pgkbDiseaseCount.put("null", "31710");
		pgkbDiseaseCount.put("Abdominal Pain,", "2");
		pgkbDiseaseCount.put("Abetalipoproteinemia,", "5");
		pgkbDiseaseCount.put("Abortion, Spontaneous,", "11");
		pgkbDiseaseCount.put("ACAD9 Deficiency,", "1");
		pgkbDiseaseCount.put("Acidosis, Renal Tubular,", "1");
		pgkbDiseaseCount.put("Acquired Immunodeficiency Syndrome,", "12");
		pgkbDiseaseCount.put("Acquired Long QT Syndrome (aLQTS),", "16");
		pgkbDiseaseCount.put("Acrocephalosyndactylia,", "2");
		pgkbDiseaseCount.put("Activated Protein C Resistance,", "3");
		pgkbDiseaseCount.put("Acute coronary syndrome,", "1158");
		pgkbDiseaseCount.put("Acute necrotizing ulcerative gingivostomatitis,", "1");
		pgkbDiseaseCount.put("Acute ulcerative gingivitis,", "1");
		pgkbDiseaseCount.put("Addison Disease,", "1");
		pgkbDiseaseCount.put("Adenocarcinoma,", "8");
		pgkbDiseaseCount.put("Adenoma,", "2");
		pgkbDiseaseCount.put("Adenomatous Polyposis Coli,", "13");
		pgkbDiseaseCount.put("Adrenal Hyperplasia, Congenital,", "2");
		pgkbDiseaseCount.put("Adrenocortical Adenoma,", "1");
		pgkbDiseaseCount.put("Adrenocortical Hyperfunction,", "1");
		pgkbDiseaseCount.put("Adrenoleukodystrophy,", "1");
		pgkbDiseaseCount.put("Adult onset Still's disease,", "1");
		pgkbDiseaseCount.put("Afibrinogenemia,", "1");
		pgkbDiseaseCount.put("Aggressive Periodontitis,", "1");
		pgkbDiseaseCount.put("Agraphia,", "2");
		pgkbDiseaseCount.put("Albuminuria,", "2");
		pgkbDiseaseCount.put("Alcohol-Related Disorders,", "3");
		pgkbDiseaseCount.put("Alcoholism,", "33");
		pgkbDiseaseCount.put("Alopecia,", "9");
		pgkbDiseaseCount.put("alpha-Thalassemia,", "3");
		pgkbDiseaseCount.put("Alzheimer Disease,", "12");
		pgkbDiseaseCount.put("Amnesia, Retrograde,", "2");
		pgkbDiseaseCount.put("Amyotrophic Lateral Sclerosis,", "1");
		pgkbDiseaseCount.put("Androgen-Insensitivity Syndrome,", "1");
		pgkbDiseaseCount.put("Anemia,", "17");
		pgkbDiseaseCount.put("Anemia, Hemolytic,", "1");
		pgkbDiseaseCount.put("Anemia, Hemolytic, Congenital Nonspherocytic,", "1");
		pgkbDiseaseCount.put("Anemia, Pernicious,", "1");
		pgkbDiseaseCount.put("Anemia, Sickle Cell,", "14");
		pgkbDiseaseCount.put("Angina Pectoris,", "17");
		pgkbDiseaseCount.put("Angina, Unstable,", "2");
		pgkbDiseaseCount.put("Angioedema,", "3");
		pgkbDiseaseCount.put("Anorexia,", "2");
		pgkbDiseaseCount.put("Anti-Glomerular Basement Membrane Disease,", "1");
		pgkbDiseaseCount.put("Anxiety Disorders,", "14");
		pgkbDiseaseCount.put("Aortic Aneurysm, Abdominal,", "1");
		pgkbDiseaseCount.put("Aphasia,", "3");
		pgkbDiseaseCount.put("Apnea,", "2");
		pgkbDiseaseCount.put("Apraxias,", "2");
		pgkbDiseaseCount.put("Arnold-Chiari Malformation,", "2");
		pgkbDiseaseCount.put("Arrhythmias, Cardiac,", "20");
		pgkbDiseaseCount.put("Arteriosclerosis,", "23");
		pgkbDiseaseCount.put("Arthritis,", "5");
		pgkbDiseaseCount.put("Arthritis, Gouty,", "49");
		pgkbDiseaseCount.put("Arthritis, Juvenile Rheumatoid,", "9");
		pgkbDiseaseCount.put("Arthritis, Psoriatic,", "7");
		pgkbDiseaseCount.put("Arthritis, Reactive,", "1");
		pgkbDiseaseCount.put("Arthritis, Rheumatoid,", "199");
		pgkbDiseaseCount.put("Articulation Disorders,", "1");
		pgkbDiseaseCount.put("Ascites,", "2");
		pgkbDiseaseCount.put("Asperger's disorder,", "1");
		pgkbDiseaseCount.put("Asphyxia,", "2");
		pgkbDiseaseCount.put("aspirin-induced asthma,", "25");
		pgkbDiseaseCount.put("Asthenia,", "7");
		pgkbDiseaseCount.put("Asthma,", "125");
		pgkbDiseaseCount.put("Astrocytoma,", "2");
		pgkbDiseaseCount.put("Ataxia,", "2");
		pgkbDiseaseCount.put("Athetosis,", "1");
		pgkbDiseaseCount.put("Atrial Fibrillation,", "36");
		pgkbDiseaseCount.put("Attention Deficit Disorder with Hyperactivity,", "23");
		pgkbDiseaseCount.put("Autism Spectrum Disorder,", "2");
		pgkbDiseaseCount.put("Autistic Disorder,", "17");
		pgkbDiseaseCount.put("AV nodal re-entry tachycardia,", "1");
		pgkbDiseaseCount.put("Back Pain,", "1");
		pgkbDiseaseCount.put("Bacteremia,", "2");
		pgkbDiseaseCount.put("Barrett Esophagus,", "1");
		pgkbDiseaseCount.put("Basal Cell Nevus Syndrome,", "1");
		pgkbDiseaseCount.put("Bell's palsy,", "1");
		pgkbDiseaseCount.put("Beta-D-mannosidosis,", "2");
		pgkbDiseaseCount.put("beta-Thalassemia,", "10");
		pgkbDiseaseCount.put("Bipolar Disorder,", "1216");
		pgkbDiseaseCount.put("Bird-fancier's lung NOS,", "1");
		pgkbDiseaseCount.put("Bone Diseases, Metabolic,", "5");
		pgkbDiseaseCount.put("Brachial Plexus Neuropathies,", "1");
		pgkbDiseaseCount.put("Brachydactyly type B,", "5");
		pgkbDiseaseCount.put("Brain Diseases,", "3");
		pgkbDiseaseCount.put("Brain Ischemia,", "2");
		pgkbDiseaseCount.put("Branchio-Oto-Renal Syndrome,", "1");
		pgkbDiseaseCount.put("Breast Neoplasms,", "184");
		pgkbDiseaseCount.put("Breast Neoplasms, Male,", "2");
		pgkbDiseaseCount.put("Brugada syndrome,", "3");
		pgkbDiseaseCount.put("Budd-Chiari Syndrome,", "1");
		pgkbDiseaseCount.put("Burkitt Lymphoma,", "6");
		pgkbDiseaseCount.put("Cachexia,", "2");
		pgkbDiseaseCount.put("Camurati-Engelmann Syndrome,", "1");
		pgkbDiseaseCount.put("Canavan Disease,", "1");
		pgkbDiseaseCount.put("Carcinoma, Hepatocellular,", "10");
		pgkbDiseaseCount.put("Carcinoma, Intraductal, Noninfiltrating,", "1");
		pgkbDiseaseCount.put("Carcinoma, Large Cell,", "2");
		pgkbDiseaseCount.put("Carcinoma, Non-Small-Cell Lung,", "105");
		pgkbDiseaseCount.put("Carcinoma, Renal Cell,", "22");
		pgkbDiseaseCount.put("Carcinoma, Small Cell,", "13");
		pgkbDiseaseCount.put("Carcinoma, Squamous Cell,", "1");
		pgkbDiseaseCount.put("Cardiomyopathies,", "5");
		pgkbDiseaseCount.put("Cardiovascular Diseases,", "30");
		pgkbDiseaseCount.put("Caroli Disease,", "1");
		pgkbDiseaseCount.put("Cat scratch disease,", "1");
		pgkbDiseaseCount.put("Cataract,", "1");
		pgkbDiseaseCount.put("Celiac Disease,", "1");
		pgkbDiseaseCount.put("Cerebral Infarction,", "1");
		pgkbDiseaseCount.put("Cerebral Palsy,", "2");
		pgkbDiseaseCount.put("Charcot-Marie-Tooth Disease,", "1");
		pgkbDiseaseCount.put("Chediak-Higashi Syndrome,", "1");
		pgkbDiseaseCount.put("Chest Pain,", "2");
		pgkbDiseaseCount.put("Cheyne-Stokes Respiration,", "2");
		pgkbDiseaseCount.put("Chorea,", "1");
		pgkbDiseaseCount.put("Choriocarcinoma,", "1");
		pgkbDiseaseCount.put("Chronic Disease,", "2");
		pgkbDiseaseCount.put("Cleft Lip,", "4");
		pgkbDiseaseCount.put("Cleft Palate,", "4");
		pgkbDiseaseCount.put("Cleft Palate, X-Linked,", "1");
		pgkbDiseaseCount.put("Cluster Headache,", "4");
		pgkbDiseaseCount.put("Coagulation Protein Disorders,", "1");
		pgkbDiseaseCount.put("Cocaine-Related Disorders,", "21");
		pgkbDiseaseCount.put("Cockayne Syndrome,", "6");
		pgkbDiseaseCount.put("Colic,", "2");
		pgkbDiseaseCount.put("Colitis, Ulcerative,", "12");
		pgkbDiseaseCount.put("Colonic Neoplasms,", "57");
		pgkbDiseaseCount.put("Colorectal Neoplasms,", "150");
		pgkbDiseaseCount.put("Colorectal Neoplasms, Hereditary Nonpolyposis,", "1");
		pgkbDiseaseCount.put("Coma,", "2");
		pgkbDiseaseCount.put("Confusion,", "2");
		pgkbDiseaseCount.put("Congenital Abnormalities,", "6");
		pgkbDiseaseCount.put("Congenital hyperammonemia, type I,", "1");
		pgkbDiseaseCount.put("congenital long QT syndrome,", "5");
		pgkbDiseaseCount.put("Congenital total lipodystrophy,", "1");
		pgkbDiseaseCount.put("Contagious porcine pyoderma,", "1");
		pgkbDiseaseCount.put("Corneal Dystrophies, Hereditary,", "1");
		pgkbDiseaseCount.put("Coronary Artery Disease,", "1201");
		pgkbDiseaseCount.put("Coronary Disease,", "32");
		pgkbDiseaseCount.put("Coronary Stenosis,", "2");
		pgkbDiseaseCount.put("Cough,", "13");
		pgkbDiseaseCount.put("Craniofacial Abnormalities,", "6");
		pgkbDiseaseCount.put("Craniofacial Dysostosis,", "1");
		pgkbDiseaseCount.put("Craniopharyngioma,", "1");
		pgkbDiseaseCount.put("Creutzfeldt-Jakob Syndrome,", "1");
		pgkbDiseaseCount.put("Cri-du-Chat Syndrome,", "2");
		pgkbDiseaseCount.put("Crigler-Najjar Syndrome,", "5");
		pgkbDiseaseCount.put("Crohn Disease,", "8");
		pgkbDiseaseCount.put("Cryptococcosis,", "1");
		pgkbDiseaseCount.put("Cryptogenic Organizing Pneumonia,", "1");
		pgkbDiseaseCount.put("Cushing Syndrome,", "1");
		pgkbDiseaseCount.put("Cyanosis,", "2");
		pgkbDiseaseCount.put("Cystic Fibrosis,", "4");
		pgkbDiseaseCount.put("Cystitis,", "11");
		pgkbDiseaseCount.put("De Lange Syndrome,", "1");
		pgkbDiseaseCount.put("Deafness,", "8");
		pgkbDiseaseCount.put("Death,", "41");
		pgkbDiseaseCount.put("Decompression Sickness,", "1");
		pgkbDiseaseCount.put("Deficiency of amylo-1,6-glucosidase,", "2");
		pgkbDiseaseCount.put("Deglutition Disorders,", "2");
		pgkbDiseaseCount.put("Dementia,", "6");
		pgkbDiseaseCount.put("Dementia, Vascular,", "1");
		pgkbDiseaseCount.put("Depression,", "53");
		pgkbDiseaseCount.put("Depressive Disorder,", "35");
		pgkbDiseaseCount.put("Depressive Disorder, Major,", "88");
		pgkbDiseaseCount.put("Dermatitis Herpetiformis,", "1");
		pgkbDiseaseCount.put("Dermatitis, Allergic Contact,", "1");
		pgkbDiseaseCount.put("Diabetes Mellitus,", "45");
		pgkbDiseaseCount.put("Diabetes Mellitus, Type 1,", "4");
		pgkbDiseaseCount.put("Diabetes Mellitus, Type 2,", "70");
		pgkbDiseaseCount.put("Diabetes, Permanent Neonatal (PNDM),", "3");
		pgkbDiseaseCount.put("Diaper rash,", "1");
		pgkbDiseaseCount.put("Diarrhea,", "76");
		pgkbDiseaseCount.put("Diffuse Cerebral Sclerosis of Schilder,", "1");
		pgkbDiseaseCount.put("Disseminated idiopathic skeletal hyperostosis,", "1");
		pgkbDiseaseCount.put("Dizziness,", "2");
		pgkbDiseaseCount.put("Double Y syndrome,", "1");
		pgkbDiseaseCount.put("Down Syndrome,", "5");
		pgkbDiseaseCount.put("Drug Hypersensitivity,", "66");
		pgkbDiseaseCount.put("Drug Resistance,", "12");
		pgkbDiseaseCount.put("Drug Toxicity,", "146");
		pgkbDiseaseCount.put("Duane Retraction Syndrome,", "1");
		pgkbDiseaseCount.put("Duodenal Ulcer,", "1");
		pgkbDiseaseCount.put("Dysgeusia,", "2");
		pgkbDiseaseCount.put("Dyskeratosis Congenita,", "1");
		pgkbDiseaseCount.put("Dyslexia,", "2");
		pgkbDiseaseCount.put("Dysplastic Nevus Syndrome,", "6");
		pgkbDiseaseCount.put("Dyspnea,", "2");
		pgkbDiseaseCount.put("Dystonic Disorders,", "1");
		pgkbDiseaseCount.put("Earache,", "1");
		pgkbDiseaseCount.put("Ebstein Anomaly,", "1");
		pgkbDiseaseCount.put("Ectodermal Dysplasia,", "1");
		pgkbDiseaseCount.put("Edema,", "5");
		pgkbDiseaseCount.put("Eisenmenger's defect,", "1");
		pgkbDiseaseCount.put("Ellis-Van Creveld Syndrome,", "1");
		pgkbDiseaseCount.put("Embolism, Air,", "1");
		pgkbDiseaseCount.put("Encephalitis,", "1");
		pgkbDiseaseCount.put("Encephalitis, Japanese,", "5");
		pgkbDiseaseCount.put("Endodermal Sinus Tumor,", "4");
		pgkbDiseaseCount.put("Endometrial Neoplasms,", "15");
		pgkbDiseaseCount.put("Enuresis,", "2");
		pgkbDiseaseCount.put("Eosinophilia-Myalgia Syndrome,", "1");
		pgkbDiseaseCount.put("Ependymoma,", "1");
		pgkbDiseaseCount.put("Epidermal Necrolysis, Toxic,", "65");
		pgkbDiseaseCount.put("Epilepsy,", "135");
		pgkbDiseaseCount.put("Epilepsy, Absence,", "1");
		pgkbDiseaseCount.put("Epistaxis,", "2");
		pgkbDiseaseCount.put("Erectile Dysfunction,", "7");
		pgkbDiseaseCount.put("Esophageal Neoplasms,", "30");
		pgkbDiseaseCount.put("Essential hypertension,", "44");
		pgkbDiseaseCount.put("Exanthema,", "13");
		pgkbDiseaseCount.put("Fabry Disease,", "1");
		pgkbDiseaseCount.put("Facial Pain,", "2");
		pgkbDiseaseCount.put("Factor XI Deficiency,", "1");
		pgkbDiseaseCount.put("Failure to Thrive,", "2");
		pgkbDiseaseCount.put("Fallopian Tube Neoplasms,", "2");
		pgkbDiseaseCount.put("Fallot's triad,", "1");
		pgkbDiseaseCount.put("Fallot's trilogy,", "1");
		pgkbDiseaseCount.put("Familial Mediterranean Fever,", "1");
		pgkbDiseaseCount.put("Fanconi Anemia,", "1");
		pgkbDiseaseCount.put("Fatigue,", "10");
		pgkbDiseaseCount.put("Favism,", "1");
		pgkbDiseaseCount.put("Felty's syndrome,", "1");
		pgkbDiseaseCount.put("Fetal Growth Retardation,", "21");
		pgkbDiseaseCount.put("Fever,", "7");
		pgkbDiseaseCount.put("Fibroadenoma,", "1");
		pgkbDiseaseCount.put("Fibrocystic disease of breast,", "1");
		pgkbDiseaseCount.put("Fibroma,", "1");
		pgkbDiseaseCount.put("Fibrous Dysplasia, Polyostotic,", "1");
		pgkbDiseaseCount.put("Fisher's syndrome,", "1");
		pgkbDiseaseCount.put("Flatulence,", "2");
		pgkbDiseaseCount.put("Flushing,", "2");
		pgkbDiseaseCount.put("Focal Dermal Hypoplasia,", "1");
		pgkbDiseaseCount.put("Fractures, Bone,", "6");
		pgkbDiseaseCount.put("Fragile X Syndrome,", "17");
		pgkbDiseaseCount.put("FragileX-associatedTremor", "1");
		pgkbDiseaseCount.put("Friedreich Ataxia,", "1");
		pgkbDiseaseCount.put("Fructose Intolerance,", "5");
		pgkbDiseaseCount.put("FTLD,", "1");
		pgkbDiseaseCount.put("Fuchs' corneal dystrophy,", "1");
		pgkbDiseaseCount.put("Fucosidosis,", "2");
		pgkbDiseaseCount.put("Gait Ataxia,", "2");
		pgkbDiseaseCount.put("Gait Disorders, Neurologic,", "1");
		pgkbDiseaseCount.put("Gangrene,", "2");
		pgkbDiseaseCount.put("Gastroesophageal Reflux,", "11");
		pgkbDiseaseCount.put("Gastrointestinal Neoplasms,", "3");
		pgkbDiseaseCount.put("Gaucher Disease,", "1");
		pgkbDiseaseCount.put("Genital Neoplasms, Female,", "4");
		pgkbDiseaseCount.put("Gerstmann Syndrome,", "1");
		pgkbDiseaseCount.put("Gestational hypertension,", "8");
		pgkbDiseaseCount.put("Giant Cell Arteritis,", "1");
		pgkbDiseaseCount.put("Giant Lymph Node Hyperplasia,", "3");
		pgkbDiseaseCount.put("Gilbert Disease,", "5");
		pgkbDiseaseCount.put("Gilbert's syndrome,", "1");
		pgkbDiseaseCount.put("Glioblastoma,", "1");
		pgkbDiseaseCount.put("Glioma,", "5");
		pgkbDiseaseCount.put("Glomerulonephritis, IGA,", "4");
		pgkbDiseaseCount.put("Glycogen Storage Disease Type I,", "2");
		pgkbDiseaseCount.put("Glycogen Storage Disease Type V,", "2");
		pgkbDiseaseCount.put("Glycogen storage disease, type III,", "1");
		pgkbDiseaseCount.put("Glycogen storage disease, type IV,", "2");
		pgkbDiseaseCount.put("Glycogen storage disease, type VII,", "1");
		pgkbDiseaseCount.put("Graft vs Host Disease,", "7");
		pgkbDiseaseCount.put("Granular Cell Tumor,", "1");
		pgkbDiseaseCount.put("Granulomatous Disease, Chronic,", "2");
		pgkbDiseaseCount.put("Graves Disease,", "1");
		pgkbDiseaseCount.put("Gulf war syndrome,", "1");
		pgkbDiseaseCount.put("H Syndrome,", "3");
		pgkbDiseaseCount.put("Hallucinations,", "2");
		pgkbDiseaseCount.put("Halo nevus,", "1");
		pgkbDiseaseCount.put("Hamartoma Syndrome, Multiple,", "2");
		pgkbDiseaseCount.put("Harada's disease,", "1");
		pgkbDiseaseCount.put("Head and Neck Neoplasms,", "39");
		pgkbDiseaseCount.put("Headache,", "2");
		pgkbDiseaseCount.put("Heart Block,", "3");
		pgkbDiseaseCount.put("Heart Defects, Congenital,", "3");
		pgkbDiseaseCount.put("Heart Diseases,", "21");
		pgkbDiseaseCount.put("Heart Failure,", "28");
		pgkbDiseaseCount.put("Heart Murmurs,", "2");
		pgkbDiseaseCount.put("Heartburn,", "2");
		pgkbDiseaseCount.put("Heerfordt's syndrome,", "1");
		pgkbDiseaseCount.put("Hemangioma,", "1");
		pgkbDiseaseCount.put("Hemoglobinuria,", "2");
		pgkbDiseaseCount.put("Hemolysis,", "10");
		pgkbDiseaseCount.put("Hemolytic-Uremic Syndrome,", "1");
		pgkbDiseaseCount.put("Hemophilia B,", "5");
		pgkbDiseaseCount.put("Hemoptysis,", "2");
		pgkbDiseaseCount.put("Hemorrhage,", "28");
		pgkbDiseaseCount.put("Hemorrhagic disease of the newborn due to vitamin K deficiency,", "1");
		pgkbDiseaseCount.put("Hepatic Encephalopathy,", "1");
		pgkbDiseaseCount.put("Hepatic Veno-Occlusive Disease,", "8");
		pgkbDiseaseCount.put("Hepatitis B,", "5");
		pgkbDiseaseCount.put("Hepatitis B, Chronic,", "5");
		pgkbDiseaseCount.put("Hepatitis C,", "12");
		pgkbDiseaseCount.put("Hepatitis C, Chronic,", "14");
		pgkbDiseaseCount.put("Hepatitis D, Chronic,", "2");
		pgkbDiseaseCount.put("Hepatitis,", "14");
		pgkbDiseaseCount.put("Hepatitis, Toxic,", "17");
		pgkbDiseaseCount.put("Hepatolenticular Degeneration,", "1");
		pgkbDiseaseCount.put("Hepatomegaly,", "4");
		pgkbDiseaseCount.put("Hereditary factor V deficiency disease,", "4");
		pgkbDiseaseCount.put("Hereditary Sensory and Autonomic Neuropathies,", "3");
		pgkbDiseaseCount.put("Heroin Dependence,", "25");
		pgkbDiseaseCount.put("Herpes zoster auricularis,", "1");
		pgkbDiseaseCount.put("Hirschsprung Disease,", "1");
		pgkbDiseaseCount.put("Histiocytosis, Langerhans-Cell,", "3");
		pgkbDiseaseCount.put("HIV Infections,", "83");
		pgkbDiseaseCount.put("HIV,", "128");
		pgkbDiseaseCount.put("Hodgkin Disease,", "37");
		pgkbDiseaseCount.put("Horner Syndrome,", "1");
		pgkbDiseaseCount.put("Hot Flashes,", "2");
		pgkbDiseaseCount.put("Huntington Disease,", "1");
		pgkbDiseaseCount.put("Hydatidiform Mole,", "1");
		pgkbDiseaseCount.put("Hydroa vacciniforme,", "1");
		pgkbDiseaseCount.put("Hyperaldosteronism,", "1");
		pgkbDiseaseCount.put("Hyperammonemia,", "9");
		pgkbDiseaseCount.put("Hyperbilirubinemia,", "34");
		pgkbDiseaseCount.put("Hypercholesterolemia,", "42");
		pgkbDiseaseCount.put("Hypercortisolism due to nonpituitary tumor,", "1");
		pgkbDiseaseCount.put("Hypereosinophilic Syndrome,", "1");
		pgkbDiseaseCount.put("Hyperglycemia,", "2");
		pgkbDiseaseCount.put("Hyperhidrosis,", "2");
		pgkbDiseaseCount.put("Hyperhomocysteinemia,", "4");
		pgkbDiseaseCount.put("Hyperkalemia,", "1");
		pgkbDiseaseCount.put("Hyperlipidemias,", "78");
		pgkbDiseaseCount.put("Hyperlipoproteinemia Type I,", "2");
		pgkbDiseaseCount.put("Hyperlipoproteinemia Type II,", "4");
		pgkbDiseaseCount.put("Hyperlipoproteinemia Type IV,", "1");
		pgkbDiseaseCount.put("Hyperlipoproteinemias,", "1");
		pgkbDiseaseCount.put("Hyperphagia,", "2");
		pgkbDiseaseCount.put("Hyperprolactinemia,", "5");
		pgkbDiseaseCount.put("Hypersensitivity,", "4");
		pgkbDiseaseCount.put("Hypersensitivity, Delayed,", "1");
		pgkbDiseaseCount.put("Hypersensitivity, Immediate,", "2");
		pgkbDiseaseCount.put("Hypertelorism,", "1");
		pgkbDiseaseCount.put("Hypertension,", "102");
		pgkbDiseaseCount.put("Hypertension, Pulmonary,", "1");
		pgkbDiseaseCount.put("Hypertriglyceridemia,", "23");
		pgkbDiseaseCount.put("Hyperventilation,", "2");
		pgkbDiseaseCount.put("Hypesthesia,", "3");
		pgkbDiseaseCount.put("Hypoaldosteronism,", "1");
		pgkbDiseaseCount.put("Hypobetalipoproteinemias,", "5");
		pgkbDiseaseCount.put("Hypoglycemia,", "6");
		pgkbDiseaseCount.put("Hypohidrotic X-linked ectodermal dysplasia,", "1");
		pgkbDiseaseCount.put("Hypokalemia,", "1");
		pgkbDiseaseCount.put("Hypokalemic Periodic Paralysis,", "1");
		pgkbDiseaseCount.put("Hypophosphatemia, Familial,", "3");
		pgkbDiseaseCount.put("Hypopituitarism,", "1");
		pgkbDiseaseCount.put("Hypotension,", "8");
		pgkbDiseaseCount.put("Ichthyosis, X-Linked,", "2");
		pgkbDiseaseCount.put("Idiopathic livedo reticularis with systemic involvement,", "1");
		pgkbDiseaseCount.put("Immune System Diseases,", "2");
		pgkbDiseaseCount.put("Induratio penis plastica,", "1");
		pgkbDiseaseCount.put("Infantile cortical hyperostosis,", "1");
		pgkbDiseaseCount.put("Infantile Refsum's disease,", "1");
		pgkbDiseaseCount.put("Infection by Trypanosoma equiperdum,", "1");
		pgkbDiseaseCount.put("Infection,", "4");
		pgkbDiseaseCount.put("Infectious Mononucleosis,", "1");
		pgkbDiseaseCount.put("Inflammation,", "7");
		pgkbDiseaseCount.put("Inflammatory Bowel Diseases,", "16");
		pgkbDiseaseCount.put("Influenza, Human,", "8");
		pgkbDiseaseCount.put("Intracranial Hemorrhages,", "21");
		pgkbDiseaseCount.put("Ischemia,", "2");
		pgkbDiseaseCount.put("Jaundice,", "3");
		pgkbDiseaseCount.put("Jaundice, Neonatal,", "5");
		pgkbDiseaseCount.put("Job's Syndrome,", "4");
		pgkbDiseaseCount.put("Johne's disease,", "1");
		pgkbDiseaseCount.put("Juvenile epithelial corneal dystrophy,", "1");
		pgkbDiseaseCount.put("Kallmann Syndrome,", "1");
		pgkbDiseaseCount.put("Kartagener Syndrome,", "1");
		pgkbDiseaseCount.put("Kidney Diseases,", "6");
		pgkbDiseaseCount.put("Kidney Failure, Chronic,", "55");
		pgkbDiseaseCount.put("Kidney Transplantation,", "59");
		pgkbDiseaseCount.put("Klatskin's tumour,", "1");
		pgkbDiseaseCount.put("Klinefelter Syndrome,", "1");
		pgkbDiseaseCount.put("Klippel-Feil Syndrome,", "1");
		pgkbDiseaseCount.put("Kojevnikov's epilepsy,", "1");
		pgkbDiseaseCount.put("Lactose Intolerance,", "1");
		pgkbDiseaseCount.put("Langer-Giedion Syndrome,", "1");
		pgkbDiseaseCount.put("Large granular lymphocytic leukemia,", "2");
		pgkbDiseaseCount.put("Leber's amaurosis,", "1");
		pgkbDiseaseCount.put("Leigh Disease,", "1");
		pgkbDiseaseCount.put("Leprosy,", "1");
		pgkbDiseaseCount.put("Leriche Syndrome,", "1");
		pgkbDiseaseCount.put("Lesch-Nyhan Syndrome,", "1");
		pgkbDiseaseCount.put("Leukemia L1210,", "1");
		pgkbDiseaseCount.put("Leukemia,", "16");
		pgkbDiseaseCount.put("Leukemia, B-Cell,", "5");
		pgkbDiseaseCount.put("Leukemia, B-Cell, Acute,", "10");
		pgkbDiseaseCount.put("Leukemia, Biphenotypic, Acute,", "5");
		pgkbDiseaseCount.put("Leukemia, Erythroblastic, Acute,", "1");
		pgkbDiseaseCount.put("Leukemia, Lymphocytic, Chronic, B-Cell,", "11");
		pgkbDiseaseCount.put("Leukemia, Lymphoid,", "8");
		pgkbDiseaseCount.put("Leukemia, Myelogenous, Chronic, BCR-ABL Positive,", "9");
		pgkbDiseaseCount.put("Leukemia, Myeloid,", "6");
		pgkbDiseaseCount.put("Leukemia, Myeloid, Acute,", "27");
		pgkbDiseaseCount.put("Leukemia, Myeloid, Chronic, Atypical, BCR-ABL Negative,", "1");
		pgkbDiseaseCount.put("Leukemia, Myelomonocytic, Chronic,", "1");
		pgkbDiseaseCount.put("Leukemia, Promyelocytic, Acute,", "2");
		pgkbDiseaseCount.put("Leukemia-Lymphoma, Adult T-Cell,", "1");
		pgkbDiseaseCount.put("Leukodystrophy, Globoid Cell,", "1");
		pgkbDiseaseCount.put("Leukodystrophy, Metachromatic,", "3");
		pgkbDiseaseCount.put("Leukoencephalopathy, Progressive Multifocal,", "1");
		pgkbDiseaseCount.put("Leukopenia,", "16");
		pgkbDiseaseCount.put("Leydig Cell Tumor,", "1");
		pgkbDiseaseCount.put("Lipoma,", "1");
		pgkbDiseaseCount.put("Liposarcoma,", "1");
		pgkbDiseaseCount.put("Liver Cirrhosis,", "14");
		pgkbDiseaseCount.put("Liver Diseases, Alcoholic,", "11");
		pgkbDiseaseCount.put("Long QT Syndrome,", "10");
		pgkbDiseaseCount.put("Low Back Pain,", "2");
		pgkbDiseaseCount.put("Lown-Ganong-Levine syndrome,", "1");
		pgkbDiseaseCount.put("Ludwig's angina,", "1");
		pgkbDiseaseCount.put("Lung Neoplasms,", "44");
		pgkbDiseaseCount.put("Lupus Erythematosus, Systemic,", "17");
		pgkbDiseaseCount.put("Lutembacher Syndrome,", "1");
		pgkbDiseaseCount.put("Lyme Disease,", "1");
		pgkbDiseaseCount.put("Lymphedema,", "1");
		pgkbDiseaseCount.put("Lymphocytic Choriomeningitis,", "1");
		pgkbDiseaseCount.put("Lymphoma,", "8");
		pgkbDiseaseCount.put("Lymphoma, B-Cell,", "32");
		pgkbDiseaseCount.put("Lymphoma, B-Cell, Marginal Zone,", "5");
		pgkbDiseaseCount.put("Lymphoma, Follicular,", "18");
		pgkbDiseaseCount.put("Lymphoma, Large B-Cell, Diffuse,", "21");
		pgkbDiseaseCount.put("Lymphoma, Large-Cell, Anaplastic,", "2");
		pgkbDiseaseCount.put("Lymphoma, Large-Cell, Diffuse,", "14");
		pgkbDiseaseCount.put("Lymphoma, Non-Hodgkin,", "39");
		pgkbDiseaseCount.put("Lymphoma, T-Cell,", "5");
		pgkbDiseaseCount.put("Lymphoma, T-Cell, Cutaneous,", "1");
		pgkbDiseaseCount.put("Lymphoma, T-Cell, Peripheral,", "2");
		pgkbDiseaseCount.put("Lymphoproliferative Disorders,", "2");
		pgkbDiseaseCount.put("Machado-Joseph Disease,", "2");
		pgkbDiseaseCount.put("Macular Degeneration,", "45");
		pgkbDiseaseCount.put("Maculopapular Exanthema,", "61");
		pgkbDiseaseCount.put("Malaria,", "5");
		pgkbDiseaseCount.put("Malaria, Falciparum,", "3");
		pgkbDiseaseCount.put("Male Urogenital Diseases,", "2");
		pgkbDiseaseCount.put("Malignant Hyperthermia,", "1");
		pgkbDiseaseCount.put("Malocclusion,", "1");
		pgkbDiseaseCount.put("Malocclusion, Angle class I,", "1");
		pgkbDiseaseCount.put("Mannosidosis,", "2");
		pgkbDiseaseCount.put("Marek's disease,", "1");
		pgkbDiseaseCount.put("Marfan Syndrome,", "1");
		pgkbDiseaseCount.put("Marijuana Abuse,", "7");
		pgkbDiseaseCount.put("MÃ¼nchausen's syndrome,", "1");
		pgkbDiseaseCount.put("Meckel Diverticulum,", "1");
		pgkbDiseaseCount.put("Meigs Syndrome,", "1");
		pgkbDiseaseCount.put("Melanoma,", "1");
		pgkbDiseaseCount.put("Melkersson's syndrome,", "1");
		pgkbDiseaseCount.put("Melorheostosis,", "1");
		pgkbDiseaseCount.put("Meniere Disease,", "1");
		pgkbDiseaseCount.put("Meningism,", "3");
		pgkbDiseaseCount.put("Menkes Kinky Hair Syndrome,", "1");
		pgkbDiseaseCount.put("Mental Disorders,", "15");
		pgkbDiseaseCount.put("Mental Retardation, X-Linked,", "2");
		pgkbDiseaseCount.put("Mental Retardation, X-Linked, Nonsyndromic,", "1");
		pgkbDiseaseCount.put("Mesothelioma,", "8");
		pgkbDiseaseCount.put("Metabolic Syndrome X,", "5");
		pgkbDiseaseCount.put("metabolic syndrome,", "7");
		pgkbDiseaseCount.put("Methemoglobinemia,", "1");
		pgkbDiseaseCount.put("Migraine with Aura,", "12");
		pgkbDiseaseCount.put("Mikulicz's disease,", "1");
		pgkbDiseaseCount.put("Mitochondrial Diseases,", "4");
		pgkbDiseaseCount.put("Mitochondrial Myopathies,", "1");
		pgkbDiseaseCount.put("Mitral Valve Prolapse,", "1");
		pgkbDiseaseCount.put("Monckeberg's medial sclerosis,", "1");
		pgkbDiseaseCount.put("Mood Disorders,", "1");
		pgkbDiseaseCount.put("Mucocutaneous Lymph Node Syndrome,", "7");
		pgkbDiseaseCount.put("Mucolipidoses,", "2");
		pgkbDiseaseCount.put("Mucopolysaccharidosis I,", "6");
		pgkbDiseaseCount.put("Mucopolysaccharidosis II,", "1");
		pgkbDiseaseCount.put("Mucopolysaccharidosis VII,", "1");
		pgkbDiseaseCount.put("Multiple Endocrine Neoplasia Type 1,", "2");
		pgkbDiseaseCount.put("Multiple Myeloma,", "53");
		pgkbDiseaseCount.put("Multiple Sclerosis,", "72");
		pgkbDiseaseCount.put("Muscular Atrophy, Spinal,", "1");
		pgkbDiseaseCount.put("Muscular Diseases,", "5");
		pgkbDiseaseCount.put("Muscular Dystrophy, Duchenne,", "4");
		pgkbDiseaseCount.put("Muscular Dystrophy, Emery-Dreifuss,", "8");
		pgkbDiseaseCount.put("Mutism,", "1");
		pgkbDiseaseCount.put("Myalgia unspecified,", "22");
		pgkbDiseaseCount.put("Myasthenia Gravis,", "8");
		pgkbDiseaseCount.put("Myelodysplastic Syndromes,", "3");
		pgkbDiseaseCount.put("Myeloproliferative Disorders,", "1");
		pgkbDiseaseCount.put("Myelosuppression,", "10");
		pgkbDiseaseCount.put("Myocardial Infarction,", "68");
		pgkbDiseaseCount.put("Myocardial Ischemia,", "12");
		pgkbDiseaseCount.put("Myoclonic Epilepsy, Juvenile,", "1");
		pgkbDiseaseCount.put("Myoglobinuria,", "2");
		pgkbDiseaseCount.put("Myokymia,", "1");
		pgkbDiseaseCount.put("Myopathy, Central Core,", "2");
		pgkbDiseaseCount.put("Myopathysecondarytodeficiencyofseleniumand", "2");
		pgkbDiseaseCount.put("Myotonia Congenita,", "1");
		pgkbDiseaseCount.put("Myotonic Disorders,", "1");
		pgkbDiseaseCount.put("Myotonic Dystrophy,", "1");
		pgkbDiseaseCount.put("Naevus of Ota,", "1");
		pgkbDiseaseCount.put("Narcolepsy,", "1");
		pgkbDiseaseCount.put("Nasopharyngeal Neoplasms,", "1");
		pgkbDiseaseCount.put("Nausea,", "12");
		pgkbDiseaseCount.put("Neck Pain,", "1");
		pgkbDiseaseCount.put("Neoplasm Metastasis,", "1");
		pgkbDiseaseCount.put("Neoplasm, Residual,", "1");
		pgkbDiseaseCount.put("Neoplasms,", "166");
		pgkbDiseaseCount.put("Neoplasms, Second Primary,", "7");
		pgkbDiseaseCount.put("Nephritis, Interstitial,", "1");
		pgkbDiseaseCount.put("Nephrosclerosis,", "1");
		pgkbDiseaseCount.put("nephrotoxicity,", "21");
		pgkbDiseaseCount.put("Neural Tube Defects,", "15");
		pgkbDiseaseCount.put("Neurofibromatosis 1,", "3");
		pgkbDiseaseCount.put("Neurofibromatosis 2,", "1");
		pgkbDiseaseCount.put("Neuroleptic Malignant Syndrome,", "1");
		pgkbDiseaseCount.put("Neuromuscular Diseases,", "1");
		pgkbDiseaseCount.put("Neuromyelitis Optica,", "1");
		pgkbDiseaseCount.put("neuropathic pain,", "2");
		pgkbDiseaseCount.put("Neurotoxicity Syndromes,", "20");
		pgkbDiseaseCount.put("Neutropenia,", "92");
		pgkbDiseaseCount.put("Nevus, Pigmented,", "1");
		pgkbDiseaseCount.put("Niemann-Pick disease, type B,", "5");
		pgkbDiseaseCount.put("Niemann-Pick Diseases,", "10");
		pgkbDiseaseCount.put("Noonan Syndrome,", "1");
		pgkbDiseaseCount.put("null", "246");
		pgkbDiseaseCount.put("Oat cell carcinoma of lung,", "1");
		pgkbDiseaseCount.put("Obesity,", "8");
		pgkbDiseaseCount.put("Obsessive-Compulsive Disorder,", "1");
		pgkbDiseaseCount.put("Obstetric Labor Complications,", "2");
		pgkbDiseaseCount.put("Ocular Motility Disorders,", "1");
		pgkbDiseaseCount.put("Ophthalmoplegia, Chronic Progressive External,", "1");
		pgkbDiseaseCount.put("Opioid-Related Disorders,", "2");
		pgkbDiseaseCount.put("Optic Atrophy, Hereditary, Leber,", "1");
		pgkbDiseaseCount.put("Organ Transplantation,", "21");
		pgkbDiseaseCount.put("Ornithine carbamoyltransferase deficiency,", "1");
		pgkbDiseaseCount.put("Osteitis Deformans,", "9");
		pgkbDiseaseCount.put("Osteoarthritis,", "4");
		pgkbDiseaseCount.put("Osteogenesis Imperfecta,", "1");
		pgkbDiseaseCount.put("Osteonecrosis,", "35");
		pgkbDiseaseCount.put("Osteoporosis,", "35");
		pgkbDiseaseCount.put("Osteoporosis, Postmenopausal,", "20");
		pgkbDiseaseCount.put("Osteosarcoma,", "39");
		pgkbDiseaseCount.put("Ototoxicity,", "7");
		pgkbDiseaseCount.put("Ovarian hyperstimulation syndrome,", "1");
		pgkbDiseaseCount.put("Ovarian Neoplasms,", "68");
		pgkbDiseaseCount.put("Paget's disease NOS,", "1");
		pgkbDiseaseCount.put("Paget's disease OS,", "1");
		pgkbDiseaseCount.put("Paget's Disease, Extramammary,", "1");
		pgkbDiseaseCount.put("Paget's Disease, Mammary,", "1");
		pgkbDiseaseCount.put("Pain Insensitivity, Congenital,", "3");
		pgkbDiseaseCount.put("Pain,", "28");
		pgkbDiseaseCount.put("Pain, Postoperative,", "8");
		pgkbDiseaseCount.put("Pallor,", "2");
		pgkbDiseaseCount.put("Palmar-plantar erythrodysaesthesia syndrome,", "1");
		pgkbDiseaseCount.put("Pancoast Syndrome,", "1");
		pgkbDiseaseCount.put("Pancreatic Neoplasms,", "79");
		pgkbDiseaseCount.put("Panic Disorder,", "2");
		pgkbDiseaseCount.put("Paralysis,", "1");
		pgkbDiseaseCount.put("Paresthesia,", "2");
		pgkbDiseaseCount.put("Parkinson Disease,", "19");
		pgkbDiseaseCount.put("Parkinson Disease, Secondary,", "1");
		pgkbDiseaseCount.put("Pendred's syndrome,", "1");
		pgkbDiseaseCount.put("Penile Induration,", "1");
		pgkbDiseaseCount.put("Peptic Ulcer,", "2");
		pgkbDiseaseCount.put("Periodontitis,", "4");
		pgkbDiseaseCount.put("Peripheral Nervous System Diseases,", "10");
		pgkbDiseaseCount.put("Peripheral Vascular Diseases,", "29");
		pgkbDiseaseCount.put("Peroxisomal Disorders,", "1");
		pgkbDiseaseCount.put("Persistent Hyperinsulinemia Hypoglycemia of Infancy,", "3");
		pgkbDiseaseCount.put("Peste des petits ruminants,", "1");
		pgkbDiseaseCount.put("Peutz-Jeghers Syndrome,", "1");
		pgkbDiseaseCount.put("Phenylketonurias,", "2");
		pgkbDiseaseCount.put("Phosphatidylcholine-sterol acyltransferase deficiency,", "1");
		pgkbDiseaseCount.put("Pick Disease of the Brain,", "1");
		pgkbDiseaseCount.put("Pigmentation Disorders,", "1");
		pgkbDiseaseCount.put("Pituitary dependent hypercortisolism,", "1");
		pgkbDiseaseCount.put("Polycythemia Vera,", "1");
		pgkbDiseaseCount.put("Polyendocrinopathies, Autoimmune,", "2");
		pgkbDiseaseCount.put("Polyuria,", "2");
		pgkbDiseaseCount.put("Porphyria Cutanea Tarda,", "1");
		pgkbDiseaseCount.put("Porphyria, Erythropoietic,", "1");
		pgkbDiseaseCount.put("Posterior inferior cerebellar artery syndrome,", "1");
		pgkbDiseaseCount.put("Postoperative Nausea and Vomiting,", "9");
		pgkbDiseaseCount.put("Prader-Willi Syndrome,", "1");
		pgkbDiseaseCount.put("Pre-Eclampsia,", "5");
		pgkbDiseaseCount.put("Precursor Cell Lymphoblastic Leukemia-Lymphoma,", "93");
		pgkbDiseaseCount.put("Precursor T-Cell Lymphoblastic Leukemia-Lymphoma,", "4");
		pgkbDiseaseCount.put("Prinzmetal angina,", "1");
		pgkbDiseaseCount.put("Progressive cerebellar tremor,", "1");
		pgkbDiseaseCount.put("Prolactinoma,", "1");
		pgkbDiseaseCount.put("Propionic acidemia,", "1");
		pgkbDiseaseCount.put("Prostatic Neoplasms,", "52");
		pgkbDiseaseCount.put("Protein S Deficiency,", "1");
		pgkbDiseaseCount.put("Proteinuria,", "2");
		pgkbDiseaseCount.put("Pruritus ani,", "3");
		pgkbDiseaseCount.put("Pruritus of vulva,", "3");
		pgkbDiseaseCount.put("Pruritus,", "3");
		pgkbDiseaseCount.put("Pseudorabies,", "1");
		pgkbDiseaseCount.put("Psoriasis,", "43");
		pgkbDiseaseCount.put("Psychotic Disorders,", "30");
		pgkbDiseaseCount.put("Pulmonary Disease, Chronic Obstructive,", "8");
		pgkbDiseaseCount.put("Pulmonary Embolism,", "23");
		pgkbDiseaseCount.put("Pulmonary Valve Insufficiency,", "1");
		pgkbDiseaseCount.put("Purine-Pyrimidine Metabolism, Inborn Errors,", "4");
		pgkbDiseaseCount.put("Pyruvate dehydrogenase complex deficiency,", "1");
		pgkbDiseaseCount.put("Radial styloid tenosynovitis,", "1");
		pgkbDiseaseCount.put("Raynaud Disease,", "1");
		pgkbDiseaseCount.put("Rectal Neoplasms,", "19");
		pgkbDiseaseCount.put("Recurrence,", "14");
		pgkbDiseaseCount.put("Reflex, Abnormal,", "3");
		pgkbDiseaseCount.put("Reflex, Babinski,", "1");
		pgkbDiseaseCount.put("Refsum Disease,", "2");
		pgkbDiseaseCount.put("Respiratory Insufficiency,", "2");
		pgkbDiseaseCount.put("Restless Legs Syndrome,", "1");
		pgkbDiseaseCount.put("Retinopathy of Prematurity,", "2");
		pgkbDiseaseCount.put("Rett Syndrome,", "1");
		pgkbDiseaseCount.put("Reye Syndrome,", "1");
		pgkbDiseaseCount.put("Rhabdomyolysis,", "3");
		pgkbDiseaseCount.put("Rheumatic Heart Disease,", "1");
		pgkbDiseaseCount.put("Rheumatoid pneumoconiosis,", "1");
		pgkbDiseaseCount.put("Rickets,", "4");
		pgkbDiseaseCount.put("Roberts-SC phocomelia syndrome,", "1");
		pgkbDiseaseCount.put("Salmon patch naevus,", "1");
		pgkbDiseaseCount.put("Sandhoff Disease,", "6");
		pgkbDiseaseCount.put("Sarcoidosis,", "1");
		pgkbDiseaseCount.put("Sarcoma, Ewing's,", "1");
		pgkbDiseaseCount.put("Sarcoma, Kaposi,", "1");
		pgkbDiseaseCount.put("Scabies,", "4");
		pgkbDiseaseCount.put("Scheuermann Disease,", "1");
		pgkbDiseaseCount.put("Schizophrenia,", "212");
		pgkbDiseaseCount.put("Scleroderma, Localized,", "1");
		pgkbDiseaseCount.put("Seizures,", "12");
		pgkbDiseaseCount.put("Seizures, Febrile,", "5");
		pgkbDiseaseCount.put("Sepsis,", "6");
		pgkbDiseaseCount.put("Severe Acute Respiratory Syndrome,", "1");
		pgkbDiseaseCount.put("Severe Combined Immunodeficiency,", "5");
		pgkbDiseaseCount.put("Sezary Syndrome,", "1");
		pgkbDiseaseCount.put("Shock,", "2");
		pgkbDiseaseCount.put("Shock, Cardiogenic,", "2");
		pgkbDiseaseCount.put("Shock, Septic,", "2");
		pgkbDiseaseCount.put("Shortened QT interval,", "5");
		pgkbDiseaseCount.put("Sick Sinus Syndrome,", "3");
		pgkbDiseaseCount.put("Sickle Cell Trait,", "1");
		pgkbDiseaseCount.put("SjÃ¶gren-Larsson syndrome,", "1");
		pgkbDiseaseCount.put("Sjogren's Syndrome,", "1");
		pgkbDiseaseCount.put("Sleep Apnea Syndromes,", "2");
		pgkbDiseaseCount.put("Sleep Apnea, Obstructive,", "1");
		pgkbDiseaseCount.put("Sleep Disorders,", "10");
		pgkbDiseaseCount.put("Sneezing,", "2");
		pgkbDiseaseCount.put("Spasm,", "2");
		pgkbDiseaseCount.put("Spasms, Infantile,", "1");
		pgkbDiseaseCount.put("Spastic Paraplegia, Hereditary,", "2");
		pgkbDiseaseCount.put("Spinal Injuries,", "1");
		pgkbDiseaseCount.put("Spinal Muscular Atrophies of Childhood,", "1");
		pgkbDiseaseCount.put("Spinocerebellar Degenerations,", "1");
		pgkbDiseaseCount.put("Splenomegaly,", "2");
		pgkbDiseaseCount.put("Spondylitis, Ankylosing,", "1");
		pgkbDiseaseCount.put("Staphylococcal Scalded Skin Syndrome,", "1");
		pgkbDiseaseCount.put("Status Epilepticus,", "1");
		pgkbDiseaseCount.put("Stevens-Johnson Syndrome,", "93");
		pgkbDiseaseCount.put("Still's Disease, Adult-Onset,", "1");
		pgkbDiseaseCount.put("Stomach Neoplasms,", "36");
		pgkbDiseaseCount.put("Stomach Ulcer,", "4");
		pgkbDiseaseCount.put("Stomatitis,", "3");
		pgkbDiseaseCount.put("Stroke,", "42");
		pgkbDiseaseCount.put("Subacute Sclerosing Panencephalitis,", "1");
		pgkbDiseaseCount.put("Substance Abuse, Intravenous,", "1");
		pgkbDiseaseCount.put("Substance-Related Disorders,", "23");
		pgkbDiseaseCount.put("Sudden Infant Death,", "5");
		pgkbDiseaseCount.put("Sweet Syndrome,", "1");
		pgkbDiseaseCount.put("Syncope,", "2");
		pgkbDiseaseCount.put("Syncope, Vasovagal,", "3");
		pgkbDiseaseCount.put("Syringomyelia,", "1");
		pgkbDiseaseCount.put("Tachycardia,", "2");
		pgkbDiseaseCount.put("Tachycardia, Ventricular,", "5");
		pgkbDiseaseCount.put("Tailor's bunion,", "1");
		pgkbDiseaseCount.put("Takayasu Arteritis,", "1");
		pgkbDiseaseCount.put("tardive dyskinesia,", "20");
		pgkbDiseaseCount.put("Tay-Sachs Disease,", "1");
		pgkbDiseaseCount.put("Telangiectasia, Hereditary Hemorrhagic,", "1");
		pgkbDiseaseCount.put("Temporomandibular joint-pain-dysfunction syndrome,", "1");
		pgkbDiseaseCount.put("Testicular Neoplasms,", "3");
		pgkbDiseaseCount.put("Tetralogy of Fallot,", "1");
		pgkbDiseaseCount.put("Thanatophoric Dysplasia,", "1");
		pgkbDiseaseCount.put("therapy-related acute myeloid leukemia (t-ML),", "5");
		pgkbDiseaseCount.put("Thiamine Deficiency,", "1");
		pgkbDiseaseCount.put("Thrombocytopenia,", "44");
		pgkbDiseaseCount.put("Thromboembolism,", "25");
		pgkbDiseaseCount.put("Thrombosis,", "18");
		pgkbDiseaseCount.put("Thyroiditis, Autoimmune,", "1");
		pgkbDiseaseCount.put("Tietze's disease,", "1");
		pgkbDiseaseCount.put("Tinnitus,", "2");
		pgkbDiseaseCount.put("Tobacco Use Disorder,", "46");
		pgkbDiseaseCount.put("Tonic pupillary reaction,", "1");
		pgkbDiseaseCount.put("Torsades de Pointes,", "21");
		pgkbDiseaseCount.put("Tourette Syndrome,", "1");
		pgkbDiseaseCount.put("Toxic liver disease,", "66");
		pgkbDiseaseCount.put("Toxoplasma encephalitis,", "1");
		pgkbDiseaseCount.put("Transplantation,", "29");
		pgkbDiseaseCount.put("Tremor,", "3");
		pgkbDiseaseCount.put("Tricuspid Valve Insufficiency,", "2");
		pgkbDiseaseCount.put("Tuberculosis cutis indurativa,", "1");
		pgkbDiseaseCount.put("Tuberculosis,", "53");
		pgkbDiseaseCount.put("Tuberous Sclerosis,", "1");
		pgkbDiseaseCount.put("Turner Syndrome,", "4");
		pgkbDiseaseCount.put("Typhus, Epidemic Louse-Borne,", "1");
		pgkbDiseaseCount.put("Tyrosinemias,", "1");
		pgkbDiseaseCount.put("Unconsciousness,", "2");
		pgkbDiseaseCount.put("Urinary Bladder Neoplasms,", "26");
		pgkbDiseaseCount.put("Urinary Incontinence,", "2");
		pgkbDiseaseCount.put("Urinary Incontinence, Stress,", "1");
		pgkbDiseaseCount.put("Urinary Retention,", "2");
		pgkbDiseaseCount.put("Urinary Tract Infections,", "2");
		pgkbDiseaseCount.put("Urologic Neoplasms,", "2");
		pgkbDiseaseCount.put("Urticaria,", "14");
		pgkbDiseaseCount.put("Uterine Cervical Neoplasms,", "7");
		pgkbDiseaseCount.put("Uterine Neoplasms,", "2");
		pgkbDiseaseCount.put("Vascular Diseases,", "49");
		pgkbDiseaseCount.put("Vasoactive intestinal peptide-secreting tumour,", "1");
		pgkbDiseaseCount.put("venous thromboembolism,", "24");
		pgkbDiseaseCount.put("Venous Thrombosis,", "23");
		pgkbDiseaseCount.put("Ventricular Fibrillation,", "3");
		pgkbDiseaseCount.put("Vincent's infection,", "1");
		pgkbDiseaseCount.put("Viremia,", "2");
		pgkbDiseaseCount.put("Vitamin B 12 Deficiency,", "5");
		pgkbDiseaseCount.put("Vitamin B deficiency NOS,", "5");
		pgkbDiseaseCount.put("Vitamin D Deficiency,", "2");
		pgkbDiseaseCount.put("Vitamin K Deficiency,", "1");
		pgkbDiseaseCount.put("Vocal Cord Paralysis,", "1");
		pgkbDiseaseCount.put("Vogt-Koyanagi-Harada disease,", "1");
		pgkbDiseaseCount.put("Volkmann's ischemic contracture following injury,", "1");
		pgkbDiseaseCount.put("Vomiting,", "8");
		pgkbDiseaseCount.put("von Hippel-Lindau Disease,", "2");
		pgkbDiseaseCount.put("von Willebrand Disease,", "7");
		pgkbDiseaseCount.put("Waardenburg's Syndrome,", "2");
		pgkbDiseaseCount.put("Waldenstrom Macroglobulinemia,", "1");
		pgkbDiseaseCount.put("Wegener Granulomatosis,", "1");
		pgkbDiseaseCount.put("Weight gain,", "18");
		pgkbDiseaseCount.put("Weil Disease,", "1");
		pgkbDiseaseCount.put("Werner Syndrome,", "1");
		pgkbDiseaseCount.put("Wernicke's disease,", "1");
		pgkbDiseaseCount.put("Wernicke's dysphasia,", "1");
		pgkbDiseaseCount.put("Whipple Disease,", "1");
		pgkbDiseaseCount.put("Wilms Tumor,", "1");
		pgkbDiseaseCount.put("Wissler-Fanconi syndrome,", "1");
		pgkbDiseaseCount.put("X-linked hyper-IgM syndrome,", "1");
		pgkbDiseaseCount.put("X-linked mental retardation with marfanoid habitus syndrome,", "1");
		pgkbDiseaseCount.put("X-linked Recessive Myopathies,", "1");
		pgkbDiseaseCount.put("X-linked severe combined immunodeficiency,", "1");
		pgkbDiseaseCount.put("Xeroderma Pigmentosum,", "1");
		pgkbDiseaseCount.put("Zellweger Syndrome,", "1");
		pgkbDiseaseCount.put("Zenker's diverticulum,", "1");

	} // END OF PGKB Disease Count

	public void setpgkbDrugCount() {

		pgkbDrugCount.put("COUNT", "58949");
		pgkbDrugCount.put("null", "31030");
		pgkbDrugCount.put("1", "6");
		pgkbDrugCount.put("1-methyloxy-4-sulfone-benzene", "11");
		pgkbDrugCount.put("10", "9");
		pgkbDrugCount.put("10-hydroxyamitriptyline", "5");
		pgkbDrugCount.put("10-hydroxynortriptyline", "5");
		pgkbDrugCount.put("2-hydroxycarbamazepine", "15");
		pgkbDrugCount.put("3", "31");
		pgkbDrugCount.put("3-((6-amino-(4-chlorobenzenesulfonyl)-2-methyl-5", "20");
		pgkbDrugCount.put("3-aminopyridine-2-carboxaldehydethiosemicarbazone", "5");
		pgkbDrugCount.put("3-beta-hydroxy-5-androsten-17-one", "12");
		pgkbDrugCount.put("3-hydroxycarbamazepine", "11");
		pgkbDrugCount.put("4-methylumbelliferone", "3");
		pgkbDrugCount.put("5-phenyl-5-(4-hydroxyphenyl)hydantoinglucuronide", "9");
		pgkbDrugCount.put("6-o-phosphorylinosinemonophosphate", "6");
		pgkbDrugCount.put("abacavir", "63");
		pgkbDrugCount.put("abarelix", "12");
		pgkbDrugCount.put("abatacept", "10");
		pgkbDrugCount.put("abciximab", "43");
		pgkbDrugCount.put("acamprosate", "6");
		pgkbDrugCount.put("acarbose", "4");
		pgkbDrugCount.put("acebutolol", "5");
		pgkbDrugCount.put("AceInhibitors", "23");
		pgkbDrugCount.put("ACEinhibitorsandcalciumchannelblockers", "1");
		pgkbDrugCount.put("ACEinhibitorsanddiuretics", "1");
		pgkbDrugCount.put("acenocoumarol", "31");
		pgkbDrugCount.put("acepromazine", "5");
		pgkbDrugCount.put("aceprometazine", "8");
		pgkbDrugCount.put("acetaminophen", "66");
		pgkbDrugCount.put("acetaminophenglucuronide", "5");
		pgkbDrugCount.put("acetaminophensulfateester", "2");
		pgkbDrugCount.put("acetanilide", "1");
		pgkbDrugCount.put("acetazolamide", "3");
		pgkbDrugCount.put("aceticacid", "1");
		pgkbDrugCount.put("acetohexamide", "4");
		pgkbDrugCount.put("acetohydroxamicacid", "2");
		pgkbDrugCount.put("acetone", "1");
		pgkbDrugCount.put("acetophenazine", "8");
		pgkbDrugCount.put("acetylcholine", "2");
		pgkbDrugCount.put("acetylcysteine", "8");
		pgkbDrugCount.put("acetyldigitoxin", "12");
		pgkbDrugCount.put("aciclovir", "3");
		pgkbDrugCount.put("acitretin", "9");
		pgkbDrugCount.put("Actinomycines", "8");
		pgkbDrugCount.put("adalimumab", "50");
		pgkbDrugCount.put("adapalene", "15");
		pgkbDrugCount.put("adefovirdipivoxil", "31");
		pgkbDrugCount.put("adenine", "6");
		pgkbDrugCount.put("adenosine", "12");
		pgkbDrugCount.put("adenosinemonophosphate", "13");
		pgkbDrugCount.put("adenosinetriphosphate", "5");
		pgkbDrugCount.put("adinazolam", "7");
		pgkbDrugCount.put("afatinib", "14");
		pgkbDrugCount.put("aflatoxinb1", "7");
		pgkbDrugCount.put("agalsidasebeta", "9");
		pgkbDrugCount.put("agomelatine", "5");
		pgkbDrugCount.put("ajmaline", "9");
		pgkbDrugCount.put("albendazole", "7");
		pgkbDrugCount.put("alcaftadine", "4");
		pgkbDrugCount.put("alclometasone", "7");
		pgkbDrugCount.put("aldesleukin", "11");
		pgkbDrugCount.put("aldosterone", "37");
		pgkbDrugCount.put("alefacept", "2");
		pgkbDrugCount.put("alemtuzumab", "7");
		pgkbDrugCount.put("alendronate", "26");
		pgkbDrugCount.put("alfacalcidol", "14");
		pgkbDrugCount.put("alfentanil", "12");
		pgkbDrugCount.put("alfuzosin", "9");
		pgkbDrugCount.put("alglucerase", "5");
		pgkbDrugCount.put("alglucosidasealfa", "5");
		pgkbDrugCount.put("aliskiren", "15");
		pgkbDrugCount.put("alitretinoin", "9");
		pgkbDrugCount.put("alizapride", "4");
		pgkbDrugCount.put("AlkylatingAgents", "8");
		pgkbDrugCount.put("allopurinol", "89");
		pgkbDrugCount.put("allylestrenol", "4");
		pgkbDrugCount.put("almitrine", "9");
		pgkbDrugCount.put("almotriptan", "8");
		pgkbDrugCount.put("alosetron", "6");
		pgkbDrugCount.put("alox5-pathwaymodifiers", "23");
		pgkbDrugCount.put("alpha-1-proteinaseinhibitor", "1");
		pgkbDrugCount.put("Alpha-adrenoreceptorantagonists", "4");
		pgkbDrugCount.put("alpha-linolenicacid", "7");
		pgkbDrugCount.put("alprazolam", "8");
		pgkbDrugCount.put("alprenolol", "1");
		pgkbDrugCount.put("alprostadil", "9");
		pgkbDrugCount.put("altretamine", "1");
		pgkbDrugCount.put("aluminium", "1");
		pgkbDrugCount.put("aluminumhydroxide", "4");
		pgkbDrugCount.put("alverine", "3");
		pgkbDrugCount.put("alvimopan", "13");
		pgkbDrugCount.put("amantadine", "9");
		pgkbDrugCount.put("ambenonium", "5");
		pgkbDrugCount.put("amcinonide", "11");
		pgkbDrugCount.put("amdinocillin", "8");
		pgkbDrugCount.put("amifostine", "1");
		pgkbDrugCount.put("amikacin", "8");
		pgkbDrugCount.put("amiloride", "10");
		pgkbDrugCount.put("aminobenzoicacid", "2");
		pgkbDrugCount.put("aminocaproicacid", "36");
		pgkbDrugCount.put("aminoglutethimide", "3");
		pgkbDrugCount.put("aminohippurate", "5");
		pgkbDrugCount.put("aminolevulinicacid", "6");
		pgkbDrugCount.put("aminophenazone", "5");
		pgkbDrugCount.put("aminophylline", "4");
		pgkbDrugCount.put("aminopterin", "1");
		pgkbDrugCount.put("aminosalicylicacid", "6");
		pgkbDrugCount.put("amiodarone", "41");
		pgkbDrugCount.put("amisulpride", "8");
		pgkbDrugCount.put("amitriptyline", "33");
		pgkbDrugCount.put("amlexanox", "15");
		pgkbDrugCount.put("amlodipine", "16");
		pgkbDrugCount.put("ammoniumlactate", "5");
		pgkbDrugCount.put("amobarbital", "1");
		pgkbDrugCount.put("amodiaquine", "22");
		pgkbDrugCount.put("amoxapine", "7");
		pgkbDrugCount.put("amoxicillin", "61");
		pgkbDrugCount.put("amphetamine", "7");
		pgkbDrugCount.put("amphotericinb", "16");
		pgkbDrugCount.put("ampicillin", "24");
		pgkbDrugCount.put("amprenavir", "20");
		pgkbDrugCount.put("amrinone", "7");
		pgkbDrugCount.put("amsacrine", "8");
		pgkbDrugCount.put("amylnitrite", "3");
		pgkbDrugCount.put("anagrelide", "8");
		pgkbDrugCount.put("anakinra", "5");
		pgkbDrugCount.put("Analgesics", "5");
		pgkbDrugCount.put("Analgesicsandanesthetics", "9");
		pgkbDrugCount.put("anastrozole", "7");
		pgkbDrugCount.put("AngiotensinIIAntagonists", "1");
		pgkbDrugCount.put("anidulafungin", "13");
		pgkbDrugCount.put("anileridine", "9");
		pgkbDrugCount.put("anisindione", "9");
		pgkbDrugCount.put("anisotropinemethylbromide", "5");
		pgkbDrugCount.put("antazoline", "1");
		pgkbDrugCount.put("anthracyclinesandrelatedsubstances", "71");
		pgkbDrugCount.put("antiarrhythmics", "9");
		pgkbDrugCount.put("Antibiotics", "25");
		pgkbDrugCount.put("Anticholinergics", "2");
		pgkbDrugCount.put("antidepressants", "42");
		pgkbDrugCount.put("AntiemeticsAndAntinauseants", "1");
		pgkbDrugCount.put("antiepileptics", "29");
		pgkbDrugCount.put("AntifungalsForSystemicUse", "9");
		pgkbDrugCount.put("antihemophilicfactor", "5");
		pgkbDrugCount.put("Antihypertensives", "14");
		pgkbDrugCount.put("AntihypertensivesAndDiureticsInCombination", "1");
		pgkbDrugCount.put("Antiinflammatoryagents", "1138");
		pgkbDrugCount.put("antiinflammatoryandantirheumaticproducts", "11");
		pgkbDrugCount.put("Antimycobacterials", "9");
		pgkbDrugCount.put("antineoplasticagents", "29");
		pgkbDrugCount.put("antipsychotics", "76");
		pgkbDrugCount.put("antipyrine", "2");
		pgkbDrugCount.put("antithymocyteglobulin", "1");
		pgkbDrugCount.put("AntithyroidPreparations", "9");
		pgkbDrugCount.put("AntiviralsfortreatmentofHIVinfections", "7");
		pgkbDrugCount.put("antrafenine", "16");
		pgkbDrugCount.put("ap24534", "19");
		pgkbDrugCount.put("apomorphine", "13");
		pgkbDrugCount.put("apraclonidine", "1");
		pgkbDrugCount.put("aprepitant", "15");
		pgkbDrugCount.put("aprindine", "5");
		pgkbDrugCount.put("aprobarbital", "2");
		pgkbDrugCount.put("aprotinin", "4");
		pgkbDrugCount.put("arbekacin", "6");
		pgkbDrugCount.put("arbutamine", "8");
		pgkbDrugCount.put("ardeparin", "1");
		pgkbDrugCount.put("arformoterol", "14");
		pgkbDrugCount.put("argatroban", "15");
		pgkbDrugCount.put("aripiprazole", "54");
		pgkbDrugCount.put("Arseniccompounds", "3");
		pgkbDrugCount.put("arsenictrioxide", "17");
		pgkbDrugCount.put("arsenite", "4");
		pgkbDrugCount.put("arteether", "9");
		pgkbDrugCount.put("artemether", "7");
		pgkbDrugCount.put("artemisinin", "15");
		pgkbDrugCount.put("artesunate", "15");
		pgkbDrugCount.put("asparaginase", "6");
		pgkbDrugCount.put("aspartame", "6");
		pgkbDrugCount.put("aspirin", "49");
		pgkbDrugCount.put("astemizole", "30");
		pgkbDrugCount.put("atazanavir", "49");
		pgkbDrugCount.put("atenolol", "54");
		pgkbDrugCount.put("atomoxetine", "20");
		pgkbDrugCount.put("atorvastatin", "100");
		pgkbDrugCount.put("atovaquone", "13");
		pgkbDrugCount.put("atracurium", "14");
		pgkbDrugCount.put("atrasentan", "2");
		pgkbDrugCount.put("atropine", "12");
		pgkbDrugCount.put("auranofin", "8");
		pgkbDrugCount.put("axitinib", "6");
		pgkbDrugCount.put("azacitidine", "8");
		pgkbDrugCount.put("azatadine", "10");
		pgkbDrugCount.put("azathioprine", "32");
		pgkbDrugCount.put("azelaicacid", "8");
		pgkbDrugCount.put("azelastine", "16");
		pgkbDrugCount.put("azidocillin", "8");
		pgkbDrugCount.put("azithromycin", "9");
		pgkbDrugCount.put("azlocillin", "9");
		pgkbDrugCount.put("aztreonam", "5");
		pgkbDrugCount.put("bacampicillin", "10");
		pgkbDrugCount.put("bacitracin", "15");
		pgkbDrugCount.put("baclofen", "16");
		pgkbDrugCount.put("balsalazide", "13");
		pgkbDrugCount.put("bambuterol", "6");
		pgkbDrugCount.put("barbital", "1");
		pgkbDrugCount.put("bariumsulfate", "12");
		pgkbDrugCount.put("BariumsulfatecontainingX-raycontrastmedia", "1");
		pgkbDrugCount.put("basiliximab", "5");
		pgkbDrugCount.put("becaplermin", "5");
		pgkbDrugCount.put("beclomethasone", "10");
		pgkbDrugCount.put("benazepril", "18");
		pgkbDrugCount.put("bendroflumethiazide", "14");
		pgkbDrugCount.put("bentiromide", "12");
		pgkbDrugCount.put("bentoquatam", "2");
		pgkbDrugCount.put("benzaldehyde", "1");
		pgkbDrugCount.put("benzocaine", "19");
		pgkbDrugCount.put("benzodiazepinederivatives", "2");
		pgkbDrugCount.put("benzoicacid", "2");
		pgkbDrugCount.put("benzonatate", "3");
		pgkbDrugCount.put("benzphetamine", "7");
		pgkbDrugCount.put("benzquinamide", "1");
		pgkbDrugCount.put("benzthiazide", "10");
		pgkbDrugCount.put("benztropine", "15");
		pgkbDrugCount.put("benzylbenzoate", "5");
		pgkbDrugCount.put("benzylpenicilloylpolylysine", "10");
		pgkbDrugCount.put("bepotastine", "12");
		pgkbDrugCount.put("bepridil", "11");
		pgkbDrugCount.put("berberine", "7");
		pgkbDrugCount.put("BetaBlockingAgents", "43");
		pgkbDrugCount.put("betahistine", "1");
		pgkbDrugCount.put("betamethasone", "12");
		pgkbDrugCount.put("betaxolol", "7");
		pgkbDrugCount.put("bethanechol", "6");
		pgkbDrugCount.put("bethanidine", "2");
		pgkbDrugCount.put("bevacizumab", "94");
		pgkbDrugCount.put("bevantolol", "4");
		pgkbDrugCount.put("bexarotene", "10");
		pgkbDrugCount.put("bezafibrate", "11");
		pgkbDrugCount.put("bicalutamide", "15");
		pgkbDrugCount.put("bifonazole", "5");
		pgkbDrugCount.put("bimatoprost", "17");
		pgkbDrugCount.put("biotin", "15");
		pgkbDrugCount.put("biperiden", "10");
		pgkbDrugCount.put("Biricodar", "8");
		pgkbDrugCount.put("bisantrene", "8");
		pgkbDrugCount.put("bismuthsubsalicylate", "6");
		pgkbDrugCount.put("bisoprolol", "11");
		pgkbDrugCount.put("Bisphosphonates", "46");
		pgkbDrugCount.put("bivalirudin", "10");
		pgkbDrugCount.put("bleomycin", "19");
		pgkbDrugCount.put("boceprevir", "12");
		pgkbDrugCount.put("bopindolol", "3");
		pgkbDrugCount.put("bortezomib", "26");
		pgkbDrugCount.put("bosentan", "31");
		pgkbDrugCount.put("botulinumtoxintypea", "5");
		pgkbDrugCount.put("botulinumtoxintypeb", "7");
		pgkbDrugCount.put("brentuximabvedotin", "5");
		pgkbDrugCount.put("bretylium", "5");
		pgkbDrugCount.put("brimonidine", "10");
		pgkbDrugCount.put("brinzolamide", "8");
		pgkbDrugCount.put("brivanib", "44");
		pgkbDrugCount.put("bromazepam", "15");
		pgkbDrugCount.put("bromfenac", "4");
		pgkbDrugCount.put("bromocriptine", "23");
		pgkbDrugCount.put("bromodiphenhydramine", "13");
		pgkbDrugCount.put("Bromperidol", "11");
		pgkbDrugCount.put("brompheniramine", "9");
		pgkbDrugCount.put("bucindolol", "2");
		pgkbDrugCount.put("buclizine", "4");
		pgkbDrugCount.put("budesonide", "31");
		pgkbDrugCount.put("bumetanide", "10");
		pgkbDrugCount.put("bupivacaine", "7");
		pgkbDrugCount.put("bupranolol", "4");
		pgkbDrugCount.put("buprenorphine", "10");
		pgkbDrugCount.put("bupropion", "23");
		pgkbDrugCount.put("buserelin", "6");
		pgkbDrugCount.put("buspirone", "5");
		pgkbDrugCount.put("busulfan", "21");
		pgkbDrugCount.put("butabarbital", "6");
		pgkbDrugCount.put("butalbital", "3");
		pgkbDrugCount.put("butenafine", "10");
		pgkbDrugCount.put("butethal", "2");
		pgkbDrugCount.put("butoconazole", "9");
		pgkbDrugCount.put("butorphanol", "10");
		pgkbDrugCount.put("cabazitaxel", "11");
		pgkbDrugCount.put("cabergoline", "19");
		pgkbDrugCount.put("cabozantinib", "3");
		pgkbDrugCount.put("caffeine", "42");
		pgkbDrugCount.put("calcein", "10");
		pgkbDrugCount.put("calcidiol", "15");
		pgkbDrugCount.put("calcipotriol", "12");
		pgkbDrugCount.put("calcitriol", "22");
		pgkbDrugCount.put("Calcium", "8");
		pgkbDrugCount.put("calciumacetate", "8");
		pgkbDrugCount.put("calciumcarbonate", "7");
		pgkbDrugCount.put("calciumchannelblockers", "2");
		pgkbDrugCount.put("calciumchloride", "23");
		pgkbDrugCount.put("calciumgluceptate", "9");
		pgkbDrugCount.put("calciumphosphate", "1");
		pgkbDrugCount.put("camptothecin", "8");
		pgkbDrugCount.put("canakinumab", "4");
		pgkbDrugCount.put("candesartan", "23");
		pgkbDrugCount.put("candicidin", "14");
		pgkbDrugCount.put("candoxatril", "6");
		pgkbDrugCount.put("cangrelor", "14");
		pgkbDrugCount.put("cannabinoids", "11");
		pgkbDrugCount.put("capecitabine", "52");
		pgkbDrugCount.put("capreomycin", "7");
		pgkbDrugCount.put("capromab", "4");
		pgkbDrugCount.put("capsaicin", "2");
		pgkbDrugCount.put("captopril", "14");
		pgkbDrugCount.put("carbachol", "3");
		pgkbDrugCount.put("carbamazepine", "98");
		pgkbDrugCount.put("carbamazepineepoxide", "7");
		pgkbDrugCount.put("carbamazepineglucuronide", "11");
		pgkbDrugCount.put("carbenicillin", "8");
		pgkbDrugCount.put("carbetocin", "6");
		pgkbDrugCount.put("carbidopa", "5");
		pgkbDrugCount.put("carbimazole", "3");
		pgkbDrugCount.put("carbinoxamine", "4");
		pgkbDrugCount.put("carbondioxide", "1");
		pgkbDrugCount.put("carboplatin", "51");
		pgkbDrugCount.put("carboprosttromethamine", "6");
		pgkbDrugCount.put("carglumicacid", "11");
		pgkbDrugCount.put("carisoprodol", "6");
		pgkbDrugCount.put("carmustine", "4");
		pgkbDrugCount.put("carphenazine", "4");
		pgkbDrugCount.put("carprofen", "8");
		pgkbDrugCount.put("carteolol", "5");
		pgkbDrugCount.put("carvedilol", "37");
		pgkbDrugCount.put("caspofungin", "11");
		pgkbDrugCount.put("catecholamines", "2");
		pgkbDrugCount.put("cefacetrile", "6");
		pgkbDrugCount.put("cefaclor", "10");
		pgkbDrugCount.put("cefadroxil", "22");
		pgkbDrugCount.put("cefalotin", "6");
		pgkbDrugCount.put("cefamandole", "6");
		pgkbDrugCount.put("cefazolin", "12");
		pgkbDrugCount.put("cefdinir", "15");
		pgkbDrugCount.put("cefditoren", "12");
		pgkbDrugCount.put("cefepime", "10");
		pgkbDrugCount.put("cefixime", "7");
		pgkbDrugCount.put("cefmenoxime", "11");
		pgkbDrugCount.put("cefmetazole", "13");
		pgkbDrugCount.put("cefonicid", "6");
		pgkbDrugCount.put("cefoperazone", "7");
		pgkbDrugCount.put("ceforanide", "8");
		pgkbDrugCount.put("cefotaxime", "6");
		pgkbDrugCount.put("cefotetan", "7");
		pgkbDrugCount.put("cefotiam", "6");
		pgkbDrugCount.put("cefoxitin", "5");
		pgkbDrugCount.put("cefpiramide", "6");
		pgkbDrugCount.put("cefpodoxime", "7");
		pgkbDrugCount.put("cefprozil", "13");
		pgkbDrugCount.put("cefradine", "13");
		pgkbDrugCount.put("ceftazidime", "7");
		pgkbDrugCount.put("ceftibuten", "10");
		pgkbDrugCount.put("ceftizoxime", "6");
		pgkbDrugCount.put("ceftriaxone", "12");
		pgkbDrugCount.put("cefuroxime", "10");
		pgkbDrugCount.put("celecoxib", "112");
		pgkbDrugCount.put("Celiprolol", "8");
		pgkbDrugCount.put("cephalexin", "13");
		pgkbDrugCount.put("cephaloglycin", "11");
		pgkbDrugCount.put("cephapirin", "9");
		pgkbDrugCount.put("cerivastatin", "21");
		pgkbDrugCount.put("certolizumabpegol", "1");
		pgkbDrugCount.put("cerulenin", "12");
		pgkbDrugCount.put("ceruletide", "11");
		pgkbDrugCount.put("cetirizine", "7");
		pgkbDrugCount.put("cetrorelix", "13");
		pgkbDrugCount.put("cetuximab", "74");
		pgkbDrugCount.put("cevimeline", "13");
		pgkbDrugCount.put("chenodeoxycholicacid", "4");
		pgkbDrugCount.put("chlophedianol", "4");
		pgkbDrugCount.put("chloralhydrate", "1");
		pgkbDrugCount.put("chlorambucil", "11");
		pgkbDrugCount.put("chloramphenicol", "19");
		pgkbDrugCount.put("chlordiazepoxide", "6");
		pgkbDrugCount.put("chlorhexidine", "14");
		pgkbDrugCount.put("chlormerodrin", "3");
		pgkbDrugCount.put("chlormezanone", "5");
		pgkbDrugCount.put("chloroprocaine", "5");
		pgkbDrugCount.put("chloropyramine", "2");
		pgkbDrugCount.put("chloroquine", "11");
		pgkbDrugCount.put("chlorothiazide", "10");
		pgkbDrugCount.put("chlorotrianisene", "8");
		pgkbDrugCount.put("chloroxine", "5");
		pgkbDrugCount.put("chlorphenesin", "3");
		pgkbDrugCount.put("chlorpheniramine", "20");
		pgkbDrugCount.put("chlorproguanil", "4");
		pgkbDrugCount.put("chlorpromazine", "24");
		pgkbDrugCount.put("chlorpropamide", "3");
		pgkbDrugCount.put("chlorprothixene", "7");
		pgkbDrugCount.put("chlorthalidone", "11");
		pgkbDrugCount.put("chlorzoxazone", "9");
		pgkbDrugCount.put("cholecalciferol", "33");
		pgkbDrugCount.put("choline", "3");
		pgkbDrugCount.put("choriogonadotropinalfa", "18");
		pgkbDrugCount.put("ciclesonide", "11");
		pgkbDrugCount.put("ciclopirox", "3");
		pgkbDrugCount.put("cidofovir", "7");
		pgkbDrugCount.put("cilastatin", "6");
		pgkbDrugCount.put("cilazapril", "8");
		pgkbDrugCount.put("cilomilast", "4");
		pgkbDrugCount.put("cilostazol", "16");
		pgkbDrugCount.put("cimetidine", "17");
		pgkbDrugCount.put("cinacalcet", "27");
		pgkbDrugCount.put("cinalukast", "4");
		pgkbDrugCount.put("cinitapride", "4");
		pgkbDrugCount.put("cinnarizine", "3");
		pgkbDrugCount.put("cinolazepam", "12");
		pgkbDrugCount.put("cinoxacin", "4");
		pgkbDrugCount.put("ciprofloxacin", "14");
		pgkbDrugCount.put("cisapride", "18");
		pgkbDrugCount.put("cisatracuriumbesylate", "15");
		pgkbDrugCount.put("cisplatin", "118");
		pgkbDrugCount.put("citalopram", "101");
		pgkbDrugCount.put("citricacid", "3");
		pgkbDrugCount.put("cladribine", "11");
		pgkbDrugCount.put("clarithromycin", "18");
		pgkbDrugCount.put("clavulanate", "49");
		pgkbDrugCount.put("clemastine", "11");
		pgkbDrugCount.put("clenbuterol", "8");
		pgkbDrugCount.put("clidinium", "12");
		pgkbDrugCount.put("clindamycin", "12");
		pgkbDrugCount.put("clobazam", "9");
		pgkbDrugCount.put("clobetasol", "11");
		pgkbDrugCount.put("clocortolone", "16");
		pgkbDrugCount.put("clodronate", "7");
		pgkbDrugCount.put("clofarabine", "15");
		pgkbDrugCount.put("clofazimine", "5");
		pgkbDrugCount.put("clofibrate", "13");
		pgkbDrugCount.put("clomifene", "21");
		pgkbDrugCount.put("clomipramine", "42");
		pgkbDrugCount.put("clomocycline", "7");
		pgkbDrugCount.put("clonazepam", "7");
		pgkbDrugCount.put("clonidine", "15");
		pgkbDrugCount.put("clopidogrel", "43");
		pgkbDrugCount.put("clorazepate", "9");
		pgkbDrugCount.put("clotiazepam", "6");
		pgkbDrugCount.put("clotrimazole", "17");
		pgkbDrugCount.put("cloxacillin", "10");
		pgkbDrugCount.put("clozapine", "93");
		pgkbDrugCount.put("coagulationfactorix", "8");
		pgkbDrugCount.put("coagulationfactorviia", "11");
		pgkbDrugCount.put("cocaine", "27");
		pgkbDrugCount.put("codeine", "23");
		pgkbDrugCount.put("colchicine", "19");
		pgkbDrugCount.put("colesevelam", "10");
		pgkbDrugCount.put("colestipol", "4");
		pgkbDrugCount.put("colistimethate", "12");
		pgkbDrugCount.put("colistin", "14");
		pgkbDrugCount.put("collagenase", "1");
		pgkbDrugCount.put("conivaptan", "15");
		pgkbDrugCount.put("conjugatedestrogens", "21");
		pgkbDrugCount.put("coppersulfate", "2");
		pgkbDrugCount.put("corticosteroids", "35");
		pgkbDrugCount.put("corticotropin", "5");
		pgkbDrugCount.put("cortisoneacetate", "21");
		pgkbDrugCount.put("cosyntropin", "13");
		pgkbDrugCount.put("coumarin", "8");
		pgkbDrugCount.put("Coxibs", "6");
		pgkbDrugCount.put("CP-800", "1");
		pgkbDrugCount.put("creatine", "2");
		pgkbDrugCount.put("CremophorEL", "8");
		pgkbDrugCount.put("crizotinib", "26");
		pgkbDrugCount.put("cromoglicate", "10");
		pgkbDrugCount.put("crotamiton", "6");
		pgkbDrugCount.put("cryptenamine", "3");
		pgkbDrugCount.put("curcumin", "8");
		pgkbDrugCount.put("cyanocobalamin", "31");
		pgkbDrugCount.put("cyclacillin", "15");
		pgkbDrugCount.put("cyclandelate", "8");
		pgkbDrugCount.put("cyclizine", "5");
		pgkbDrugCount.put("cyclobenzaprine", "2");
		pgkbDrugCount.put("cyclopentolate", "7");
		pgkbDrugCount.put("cyclophosphamide", "133");
		pgkbDrugCount.put("cycloserine", "9");
		pgkbDrugCount.put("cyclosporine", "45");
		pgkbDrugCount.put("cyclothiazide", "14");
		pgkbDrugCount.put("cycrimine", "5");
		pgkbDrugCount.put("cyproheptadine", "7");
		pgkbDrugCount.put("cyproterone", "11");
		pgkbDrugCount.put("cysteamine", "3");
		pgkbDrugCount.put("cytarabine", "38");
		pgkbDrugCount.put("d-sorbitol", "6");
		pgkbDrugCount.put("d-xylitol", "6");
		pgkbDrugCount.put("dabigatranetexilate", "11");
		pgkbDrugCount.put("dacarbazine", "6");
		pgkbDrugCount.put("daclizumab", "2");
		pgkbDrugCount.put("dactinomycin", "30");
		pgkbDrugCount.put("dalfopristin", "12");
		pgkbDrugCount.put("dalteparin", "6");
		pgkbDrugCount.put("danazol", "11");
		pgkbDrugCount.put("dantrolene", "8");
		pgkbDrugCount.put("dapiprazole", "4");
		pgkbDrugCount.put("dapsone", "12");
		pgkbDrugCount.put("daptomycin", "7");
		pgkbDrugCount.put("darapladib", "14");
		pgkbDrugCount.put("darifenacin", "14");
		pgkbDrugCount.put("darunavir", "12");
		pgkbDrugCount.put("dasatinib", "15");
		pgkbDrugCount.put("daunorubicin", "33");
		pgkbDrugCount.put("debrisoquine", "7");
		pgkbDrugCount.put("decamethonium", "3");
		pgkbDrugCount.put("decitabine", "9");
		pgkbDrugCount.put("deferasirox", "4");
		pgkbDrugCount.put("deferoxamine", "6");
		pgkbDrugCount.put("defibrotide", "5");
		pgkbDrugCount.put("degarelix", "4");
		pgkbDrugCount.put("delavirdine", "10");
		pgkbDrugCount.put("demecariumbromide", "4");
		pgkbDrugCount.put("demeclocycline", "10");
		pgkbDrugCount.put("denileukindiftitox", "15");
		pgkbDrugCount.put("deserpidine", "12");
		pgkbDrugCount.put("desflurane", "12");
		pgkbDrugCount.put("desipramine", "31");
		pgkbDrugCount.put("deslanoside", "13");
		pgkbDrugCount.put("desloratadine", "15");
		pgkbDrugCount.put("desmethylastemizole", "14");
		pgkbDrugCount.put("desmethylnortriptyline", "2");
		pgkbDrugCount.put("desmopressin", "19");
		pgkbDrugCount.put("desogestrel", "10");
		pgkbDrugCount.put("desonide", "4");
		pgkbDrugCount.put("desoximetasone", "9");
		pgkbDrugCount.put("desoxycorticosteronepivalate", "9");
		pgkbDrugCount.put("desvenlafaxine", "6");
		pgkbDrugCount.put("dexamethasone", "42");
		pgkbDrugCount.put("dexbrompheniramine", "9");
		pgkbDrugCount.put("dexfenfluramine", "15");
		pgkbDrugCount.put("dexmedetomidine", "19");
		pgkbDrugCount.put("dexmethylphenidate", "7");
		pgkbDrugCount.put("dexrazoxane", "12");
		pgkbDrugCount.put("dextroamphetamine", "7");
		pgkbDrugCount.put("dextromethorphan", "20");
		pgkbDrugCount.put("dextrothyroxine", "10");
		pgkbDrugCount.put("dezocine", "9");
		pgkbDrugCount.put("diatrizoate", "4");
		pgkbDrugCount.put("diazepam", "23");
		pgkbDrugCount.put("diazoxide", "9");
		pgkbDrugCount.put("dibucaine", "3");
		pgkbDrugCount.put("dichloroaceticacid", "4");
		pgkbDrugCount.put("dichlorphenamide", "5");
		pgkbDrugCount.put("diclofenac", "11");
		pgkbDrugCount.put("dicloxacillin", "24");
		pgkbDrugCount.put("dicumarol", "18");
		pgkbDrugCount.put("dicyclomine", "3");
		pgkbDrugCount.put("didanosine", "5");
		pgkbDrugCount.put("dienestrol", "9");
		pgkbDrugCount.put("diethylcarbamazine", "2");
		pgkbDrugCount.put("diethylpropion", "7");
		pgkbDrugCount.put("diethylstilbestrol", "11");
		pgkbDrugCount.put("diflomotecan", "6");
		pgkbDrugCount.put("diflorasone", "17");
		pgkbDrugCount.put("diflunisal", "9");
		pgkbDrugCount.put("difluprednate", "5");
		pgkbDrugCount.put("digitoxin", "13");
		pgkbDrugCount.put("digoxin", "25");
		pgkbDrugCount.put("digoxinimmunefab", "1");
		pgkbDrugCount.put("dihydroergotamine", "23");
		pgkbDrugCount.put("dihydroergotoxine", "12");
		pgkbDrugCount.put("dihydrotachysterol", "12");
		pgkbDrugCount.put("dihydroxyaluminium", "1");
		pgkbDrugCount.put("diloxanide", "7");
		pgkbDrugCount.put("diltiazem", "44");
		pgkbDrugCount.put("dimenhydrinate", "5");
		pgkbDrugCount.put("dimercaprol", "4");
		pgkbDrugCount.put("dimethindene", "2");
		pgkbDrugCount.put("dimethylsulfoxide", "4");
		pgkbDrugCount.put("dinoprostone", "6");
		pgkbDrugCount.put("dinoprosttromethamine", "10");
		pgkbDrugCount.put("Dipeptidylpeptidase4(DPP-4)inhibitors", "1");
		pgkbDrugCount.put("diphemanilmethylsulfate", "6");
		pgkbDrugCount.put("diphenhydramine", "26");
		pgkbDrugCount.put("diphenidol", "7");
		pgkbDrugCount.put("diphenoxylate", "5");
		pgkbDrugCount.put("diphenylpyraline", "10");
		pgkbDrugCount.put("dipivefrin", "16");
		pgkbDrugCount.put("dipyridamole", "13");
		pgkbDrugCount.put("dirithromycin", "4");
		pgkbDrugCount.put("disopyramide", "14");
		pgkbDrugCount.put("disulfiram", "17");
		pgkbDrugCount.put("diuretics", "24");
		pgkbDrugCount.put("divalproexsodium", "5");
		pgkbDrugCount.put("dobutamine", "5");
		pgkbDrugCount.put("docetaxel", "93");
		pgkbDrugCount.put("docosanol", "3");
		pgkbDrugCount.put("dodecane-trimethylamine", "1");
		pgkbDrugCount.put("dofetilide", "12");
		pgkbDrugCount.put("dolasetron", "13");
		pgkbDrugCount.put("domperidone", "16");
		pgkbDrugCount.put("donepezil", "9");
		pgkbDrugCount.put("dopamine", "19");
		pgkbDrugCount.put("Dopamineagonists", "2");
		pgkbDrugCount.put("dornasealfa", "1");
		pgkbDrugCount.put("dorzolamide", "5");
		pgkbDrugCount.put("doxacurium", "5");
		pgkbDrugCount.put("doxapram", "3");
		pgkbDrugCount.put("doxazosin", "3");
		pgkbDrugCount.put("doxepin", "23");
		pgkbDrugCount.put("doxorubicin", "135");
		pgkbDrugCount.put("doxorubicinol", "6");
		pgkbDrugCount.put("doxycycline", "24");
		pgkbDrugCount.put("doxylamine", "5");
		pgkbDrugCount.put("dromostanolone", "5");
		pgkbDrugCount.put("droperidol", "9");
		pgkbDrugCount.put("drospirenone", "19");
		pgkbDrugCount.put("drotaverine", "4");
		pgkbDrugCount.put("drotrecoginalfa", "9");
		pgkbDrugCount.put("droxidopa", "9");
		pgkbDrugCount.put("DrugsForTreatmentOfTuberculosis", "15");
		pgkbDrugCount.put("Drugsusedinalcoholdependence", "2");
		pgkbDrugCount.put("Drugsusedinnicotinedependence", "4");
		pgkbDrugCount.put("duloxetine", "15");
		pgkbDrugCount.put("dutasteride", "17");
		pgkbDrugCount.put("dyclonine", "3");
		pgkbDrugCount.put("dydrogesterone", "4");
		pgkbDrugCount.put("dyphylline", "6");
		pgkbDrugCount.put("ecabet", "7");
		pgkbDrugCount.put("echothiophateiodide", "2");
		pgkbDrugCount.put("econazole", "14");
		pgkbDrugCount.put("eculizumab", "5");
		pgkbDrugCount.put("edeticacid", "2");
		pgkbDrugCount.put("edrophonium", "4");
		pgkbDrugCount.put("efalizumab", "1");
		pgkbDrugCount.put("efavirenz", "42");
		pgkbDrugCount.put("egfrinhibitors", "12");
		pgkbDrugCount.put("Elacridar", "8");
		pgkbDrugCount.put("eletriptan", "10");
		pgkbDrugCount.put("eltrombopag", "19");
		pgkbDrugCount.put("emedastine", "4");
		pgkbDrugCount.put("emtricitabine", "12");
		pgkbDrugCount.put("enalapril", "18");
		pgkbDrugCount.put("encainide", "3");
		pgkbDrugCount.put("enflurane", "10");
		pgkbDrugCount.put("enfuvirtide", "8");
		pgkbDrugCount.put("enoxacin", "8");
		pgkbDrugCount.put("enoxaparin", "7");
		pgkbDrugCount.put("enoximone", "5");
		pgkbDrugCount.put("enprofylline", "2");
		pgkbDrugCount.put("entacapone", "8");
		pgkbDrugCount.put("entecavir", "15");
		pgkbDrugCount.put("Enzymeinhibitors", "1");
		pgkbDrugCount.put("Enzymes", "6");
		pgkbDrugCount.put("ephedra", "3");
		pgkbDrugCount.put("ephedrine", "5");
		pgkbDrugCount.put("epinastine", "11");
		pgkbDrugCount.put("epinephrine", "42");
		pgkbDrugCount.put("epirubicin", "33");
		pgkbDrugCount.put("eplerenone", "15");
		pgkbDrugCount.put("epoetinalfa", "48");
		pgkbDrugCount.put("epoprostenol", "7");
		pgkbDrugCount.put("eprosartan", "7");
		pgkbDrugCount.put("eptifibatide", "45");
		pgkbDrugCount.put("ergocalciferol", "15");
		pgkbDrugCount.put("ergoloidmesylate", "10");
		pgkbDrugCount.put("ergonovine", "12");
		pgkbDrugCount.put("ergotamine", "19");
		pgkbDrugCount.put("erlotinib", "47");
		pgkbDrugCount.put("ertapenem", "10");
		pgkbDrugCount.put("erythrityltetranitrate", "7");
		pgkbDrugCount.put("erythromycin", "36");
		pgkbDrugCount.put("escitalopram", "50");
		pgkbDrugCount.put("esmolol", "8");
		pgkbDrugCount.put("esomeprazole", "15");
		pgkbDrugCount.put("estazolam", "8");
		pgkbDrugCount.put("estradiol", "21");
		pgkbDrugCount.put("estramustine", "5");
		pgkbDrugCount.put("estriol", "11");
		pgkbDrugCount.put("estrogens", "7");
		pgkbDrugCount.put("estrone", "9");
		pgkbDrugCount.put("estropipate", "7");
		pgkbDrugCount.put("eszopiclone", "18");
		pgkbDrugCount.put("etanercept", "49");
		pgkbDrugCount.put("ethacrynicacid", "2");
		pgkbDrugCount.put("ethambutol", "19");
		pgkbDrugCount.put("ethanol", "28");
		pgkbDrugCount.put("ethanolamineoleate", "1");
		pgkbDrugCount.put("ethchlorvynol", "7");
		pgkbDrugCount.put("ethinamate", "3");
		pgkbDrugCount.put("ethinylestradiol", "25");
		pgkbDrugCount.put("ethiodizedoil", "2");
		pgkbDrugCount.put("ethionamide", "5");
		pgkbDrugCount.put("ethopropazine", "5");
		pgkbDrugCount.put("ethosuximide", "6");
		pgkbDrugCount.put("ethotoin", "3");
		pgkbDrugCount.put("ethoxzolamide", "4");
		pgkbDrugCount.put("ethynodioldiacetate", "6");
		pgkbDrugCount.put("etidronicacid", "11");
		pgkbDrugCount.put("etodolac", "10");
		pgkbDrugCount.put("etomidate", "5");
		pgkbDrugCount.put("etonogestrel", "10");
		pgkbDrugCount.put("etoposide", "49");
		pgkbDrugCount.put("etoricoxib", "14");
		pgkbDrugCount.put("etravirine", "4");
		pgkbDrugCount.put("etretinate", "4");
		pgkbDrugCount.put("everolimus", "40");
		pgkbDrugCount.put("evodiamine", "6");
		pgkbDrugCount.put("exemestane", "21");
		pgkbDrugCount.put("exenatide", "4");
		pgkbDrugCount.put("ezetimibe", "20");
		pgkbDrugCount.put("famciclovir", "3");
		pgkbDrugCount.put("famotidine", "9");
		pgkbDrugCount.put("Farglitazar", "2");
		pgkbDrugCount.put("felbamate", "7");
		pgkbDrugCount.put("felodipine", "16");
		pgkbDrugCount.put("felypressin", "9");
		pgkbDrugCount.put("fencamfamine", "3");
		pgkbDrugCount.put("fenfluramine", "11");
		pgkbDrugCount.put("fenofibrate", "27");
		pgkbDrugCount.put("fenoldopam", "7");
		pgkbDrugCount.put("fenoprofen", "6");
		pgkbDrugCount.put("fenoterol", "5");
		pgkbDrugCount.put("fentanyl", "15");
		pgkbDrugCount.put("ferroussulfate", "2");
		pgkbDrugCount.put("fesoterodine", "5");
		pgkbDrugCount.put("fexofenadine", "22");
		pgkbDrugCount.put("filgrastim", "2");
		pgkbDrugCount.put("finasteride", "10");
		pgkbDrugCount.put("flavopiridol", "10");
		pgkbDrugCount.put("flavoxate", "4");
		pgkbDrugCount.put("flecainide", "21");
		pgkbDrugCount.put("fleroxacin", "9");
		pgkbDrugCount.put("floxuridine", "16");
		pgkbDrugCount.put("flucloxacillin", "61");
		pgkbDrugCount.put("fluconazole", "8");
		pgkbDrugCount.put("flucytosine", "11");
		pgkbDrugCount.put("fludarabine", "29");
		pgkbDrugCount.put("fludiazepam", "7");
		pgkbDrugCount.put("fludrocortisone", "10");
		pgkbDrugCount.put("fluindione", "15");
		pgkbDrugCount.put("flumazenil", "13");
		pgkbDrugCount.put("flumethasonepivalate", "15");
		pgkbDrugCount.put("flunarizine", "11");
		pgkbDrugCount.put("flunisolide", "17");
		pgkbDrugCount.put("flunitrazepam", "6");
		pgkbDrugCount.put("fluocinoloneacetonide", "17");
		pgkbDrugCount.put("fluocinonide", "16");
		pgkbDrugCount.put("fluorescein", "9");
		pgkbDrugCount.put("fluorometholone", "13");
		pgkbDrugCount.put("fluorouracil", "164");
		pgkbDrugCount.put("fluoxetine", "58");
		pgkbDrugCount.put("fluoxymesterone", "9");
		pgkbDrugCount.put("flupenthixol", "28");
		pgkbDrugCount.put("fluphenazine", "19");
		pgkbDrugCount.put("flurandrenolide", "13");
		pgkbDrugCount.put("flurazepam", "5");
		pgkbDrugCount.put("flurbiprofen", "16");
		pgkbDrugCount.put("fluspirilene", "7");
		pgkbDrugCount.put("flutamide", "15");
		pgkbDrugCount.put("fluticasone/salmeterol", "2");
		pgkbDrugCount.put("fluticasonepropionate", "23");
		pgkbDrugCount.put("fluvastatin", "48");
		pgkbDrugCount.put("fluvoxamine", "38");
		pgkbDrugCount.put("folicacid", "42");
		pgkbDrugCount.put("follitropinbeta", "42");
		pgkbDrugCount.put("fomepizole", "3");
		pgkbDrugCount.put("fondaparinuxsodium", "10");
		pgkbDrugCount.put("forasartan", "4");
		pgkbDrugCount.put("formaldehyde", "1");
		pgkbDrugCount.put("formicacid", "1");
		pgkbDrugCount.put("formoterol", "14");
		pgkbDrugCount.put("forskolin", "16");
		pgkbDrugCount.put("fosamprenavir", "13");
		pgkbDrugCount.put("fosaprepitant", "8");
		pgkbDrugCount.put("foscarnet", "2");
		pgkbDrugCount.put("fosfomycin", "6");
		pgkbDrugCount.put("fosinopril", "11");
		pgkbDrugCount.put("fosphenytoin", "7");
		pgkbDrugCount.put("fospropofol", "3");
		pgkbDrugCount.put("framycetin", "13");
		pgkbDrugCount.put("frovatriptan", "12");
		pgkbDrugCount.put("fructose", "4");
		pgkbDrugCount.put("fulvestrant", "25");
		pgkbDrugCount.put("furazolidone", "6");
		pgkbDrugCount.put("furosemide", "23");
		pgkbDrugCount.put("fusidicacid", "8");
		pgkbDrugCount.put("gabapentin", "5");
		pgkbDrugCount.put("gadobenatedimeglumine", "12");
		pgkbDrugCount.put("gadobutrol", "1");
		pgkbDrugCount.put("gadodiamide", "5");
		pgkbDrugCount.put("gadofosvesettrisodium", "6");
		pgkbDrugCount.put("gadopentetatedimeglumine", "7");
		pgkbDrugCount.put("gadoteridol", "7");
		pgkbDrugCount.put("gadoversetamide", "4");
		pgkbDrugCount.put("Gadoxeticacid", "5");
		pgkbDrugCount.put("galantamine", "14");
		pgkbDrugCount.put("gallaminetriethiodide", "3");
		pgkbDrugCount.put("galliumnitrate", "4");
		pgkbDrugCount.put("gamma-homolinolenicacid", "4");
		pgkbDrugCount.put("gammahydroxybutyricacid", "8");
		pgkbDrugCount.put("ganciclovir", "7");
		pgkbDrugCount.put("gatifloxacin", "19");
		pgkbDrugCount.put("gefitinib", "51");
		pgkbDrugCount.put("geldanamycin", "14");
		pgkbDrugCount.put("gemcitabine", "87");
		pgkbDrugCount.put("gemfibrozil", "13");
		pgkbDrugCount.put("gemifloxacin", "21");
		pgkbDrugCount.put("gemtuzumabozogamicin", "15");
		pgkbDrugCount.put("genistein", "1");
		pgkbDrugCount.put("gentamicin", "18");
		pgkbDrugCount.put("gentianviolet", "9");
		pgkbDrugCount.put("gestodene", "3");
		pgkbDrugCount.put("ginkgobiloba", "1");
		pgkbDrugCount.put("ginseng", "1");
		pgkbDrugCount.put("glatirameracetate", "69");
		pgkbDrugCount.put("glibenclamide", "33");
		pgkbDrugCount.put("gliclazide", "11");
		pgkbDrugCount.put("glimepiride", "20");
		pgkbDrugCount.put("glipizide", "5");
		pgkbDrugCount.put("gliquidone", "9");
		pgkbDrugCount.put("glisoxepide", "7");
		pgkbDrugCount.put("glucagonrecombinant", "3");
		pgkbDrugCount.put("glucocorticoids", "3");
		pgkbDrugCount.put("gluconicacid", "4");
		pgkbDrugCount.put("glucosamine", "4");
		pgkbDrugCount.put("glutaraldehyde", "1");
		pgkbDrugCount.put("glutathione", "10");
		pgkbDrugCount.put("glutethimide", "4");
		pgkbDrugCount.put("glycerin", "1");
		pgkbDrugCount.put("glycine", "4");
		pgkbDrugCount.put("glycodiazine", "4");
		pgkbDrugCount.put("glycopyrrolate", "7");
		pgkbDrugCount.put("glycyl-sarcosine", "1");
		pgkbDrugCount.put("gonadorelin", "12");
		pgkbDrugCount.put("gonadotropin", "2");
		pgkbDrugCount.put("goserelin", "7");
		pgkbDrugCount.put("gramicidind", "22");
		pgkbDrugCount.put("granisetron", "14");
		pgkbDrugCount.put("grapefruitjuice", "8");
		pgkbDrugCount.put("grepafloxacin", "19");
		pgkbDrugCount.put("griseofulvin", "19");
		pgkbDrugCount.put("guaifenesin", "10");
		pgkbDrugCount.put("guanabenz", "2");
		pgkbDrugCount.put("guanadrelsulfate", "3");
		pgkbDrugCount.put("guanethidine", "3");
		pgkbDrugCount.put("guanfacine", "4");
		pgkbDrugCount.put("haemophilusbconjugatevaccine(prp-d-diphtheriatoxoidconjugate)", "7");
		pgkbDrugCount.put("halazepam", "9");
		pgkbDrugCount.put("halobetasolpropionate", "14");
		pgkbDrugCount.put("halofantrine", "15");
		pgkbDrugCount.put("haloperidol", "60");
		pgkbDrugCount.put("haloprogin", "4");
		pgkbDrugCount.put("halothane", "10");
		pgkbDrugCount.put("hemophilusinfluenzaebvaccines", "5");
		pgkbDrugCount.put("heparin", "14");
		pgkbDrugCount.put("heptabarbital", "4");
		pgkbDrugCount.put("heroin", "23");
		pgkbDrugCount.put("hesperetin", "8");
		pgkbDrugCount.put("hetacillin", "8");
		pgkbDrugCount.put("hexachlorophene", "10");
		pgkbDrugCount.put("hexafluroniumbromide", "3");
		pgkbDrugCount.put("hexobarbital", "6");
		pgkbDrugCount.put("hexylcaine", "8");
		pgkbDrugCount.put("histaminephosphate", "6");
		pgkbDrugCount.put("hmgcoareductaseinhibitors", "126");
		pgkbDrugCount.put("homatropinemethylbromide", "9");
		pgkbDrugCount.put("hormonalcontraceptivesforsystemicuse", "8");
		pgkbDrugCount.put("hyaluronan", "3");
		pgkbDrugCount.put("hyaluronidase", "3");
		pgkbDrugCount.put("hydralazine", "17");
		pgkbDrugCount.put("hydrochlorothiazide", "56");
		pgkbDrugCount.put("hydrocodone", "7");
		pgkbDrugCount.put("hydrocortamate", "21");
		pgkbDrugCount.put("hydrocortisone", "41");
		pgkbDrugCount.put("hydroflumethiazide", "12");
		pgkbDrugCount.put("hydromorphone", "14");
		pgkbDrugCount.put("hydroquinidine", "4");
		pgkbDrugCount.put("hydroquinone", "1");
		pgkbDrugCount.put("hydroxocobalamin", "26");
		pgkbDrugCount.put("hydroxychloroquine", "25");
		pgkbDrugCount.put("hydroxyphenytoin", "6");
		pgkbDrugCount.put("hydroxypropylcellulose", "4");
		pgkbDrugCount.put("hydroxystilbamidineisethionate", "15");
		pgkbDrugCount.put("hydroxytryptamine", "1");
		pgkbDrugCount.put("hydroxyurea", "10");
		pgkbDrugCount.put("hydroxyzine", "8");
		pgkbDrugCount.put("hyoscyamine", "8");
		pgkbDrugCount.put("I.v.SolutionAdditives", "3");
		pgkbDrugCount.put("I.v.Solutions", "3");
		pgkbDrugCount.put("ibandronate", "5");
		pgkbDrugCount.put("ibritumomab", "6");
		pgkbDrugCount.put("ibudilast", "8");
		pgkbDrugCount.put("ibuprofen", "46");
		pgkbDrugCount.put("ibutilide", "18");
		pgkbDrugCount.put("icatibant", "12");
		pgkbDrugCount.put("icosapent", "2");
		pgkbDrugCount.put("idarubicin", "32");
		pgkbDrugCount.put("idoxuridine", "10");
		pgkbDrugCount.put("idursulfase", "3");
		pgkbDrugCount.put("ifosfamide", "29");
		pgkbDrugCount.put("ilaprazole", "6");
		pgkbDrugCount.put("iloperidone", "28");
		pgkbDrugCount.put("iloprost", "11");
		pgkbDrugCount.put("imatinib", "48");
		pgkbDrugCount.put("imidapril", "12");
		pgkbDrugCount.put("imiglucerase", "6");
		pgkbDrugCount.put("imipenem", "6");
		pgkbDrugCount.put("imipramine", "21");
		pgkbDrugCount.put("imiquimod", "9");
		pgkbDrugCount.put("immuneglobulin", "12");
		pgkbDrugCount.put("Immunoglobulins", "7");
		pgkbDrugCount.put("indacaterol", "16");
		pgkbDrugCount.put("indapamide", "13");
		pgkbDrugCount.put("indecainide", "6");
		pgkbDrugCount.put("indinavir", "50");
		pgkbDrugCount.put("indomethacin", "15");
		pgkbDrugCount.put("infliximab", "62");
		pgkbDrugCount.put("insulin", "4");
		pgkbDrugCount.put("insulin-aspart", "9");
		pgkbDrugCount.put("insulin-detemir", "9");
		pgkbDrugCount.put("insulin-glargine", "12");
		pgkbDrugCount.put("insulin-glulisine", "4");
		pgkbDrugCount.put("insulin-lispro", "7");
		pgkbDrugCount.put("insulinglargine", "10");
		pgkbDrugCount.put("insulinlysprorecombinant", "5");
		pgkbDrugCount.put("insulinrecombinant", "7");
		pgkbDrugCount.put("InsulinsAndAnalogues", "2");
		pgkbDrugCount.put("interferonalfa-2a", "14");
		pgkbDrugCount.put("interferonalfa-2b", "7");
		pgkbDrugCount.put("interferonalfa-n1", "6");
		pgkbDrugCount.put("interferonalfa-n3", "6");
		pgkbDrugCount.put("interferonalfacon-1", "10");
		pgkbDrugCount.put("interferonalpha-2b", "9");
		pgkbDrugCount.put("interferonbeta-1a", "56");
		pgkbDrugCount.put("interferonbeta-1b", "57");
		pgkbDrugCount.put("interferongamma-1b", "14");
		pgkbDrugCount.put("interferons", "9");
		pgkbDrugCount.put("inulin", "6");
		pgkbDrugCount.put("iodine", "1");
		pgkbDrugCount.put("iodipamide", "6");
		pgkbDrugCount.put("iodixanol", "10");
		pgkbDrugCount.put("iohexol", "7");
		pgkbDrugCount.put("iophendylate", "9");
		pgkbDrugCount.put("ipratropium", "13");
		pgkbDrugCount.put("irbesartan", "21");
		pgkbDrugCount.put("irinotecan", "107");
		pgkbDrugCount.put("iron", "60");
		pgkbDrugCount.put("irondextran", "7");
		pgkbDrugCount.put("isocarboxazid", "9");
		pgkbDrugCount.put("isoetharine", "12");
		pgkbDrugCount.put("isoflurane", "9");
		pgkbDrugCount.put("isoflurophate", "7");
		pgkbDrugCount.put("isometheptene", "2");
		pgkbDrugCount.put("isoniazid", "56");
		pgkbDrugCount.put("isopropamide", "4");
		pgkbDrugCount.put("isopropylalcohol", "1");
		pgkbDrugCount.put("isoproterenol", "19");
		pgkbDrugCount.put("isosorbidedinitrate", "15");
		pgkbDrugCount.put("isosorbidemononitrate", "10");
		pgkbDrugCount.put("isothipendyl", "5");
		pgkbDrugCount.put("isotretinoin", "4");
		pgkbDrugCount.put("isradipine", "14");
		pgkbDrugCount.put("itopride", "4");
		pgkbDrugCount.put("itraconazole", "23");
		pgkbDrugCount.put("ivacaftor", "23");
		pgkbDrugCount.put("ivermectin", "23");
		pgkbDrugCount.put("Ixabepilone", "13");
		pgkbDrugCount.put("josamycin", "13");
		pgkbDrugCount.put("kanamycin", "16");
		pgkbDrugCount.put("ketamine", "12");
		pgkbDrugCount.put("ketazolam", "7");
		pgkbDrugCount.put("ketoconazole", "21");
		pgkbDrugCount.put("ketoprofen", "7");
		pgkbDrugCount.put("ketorolac", "9");
		pgkbDrugCount.put("ketotifen", "10");
		pgkbDrugCount.put("kunecatechins", "2");
		pgkbDrugCount.put("l-alanine", "6");
		pgkbDrugCount.put("l-arginine", "12");
		pgkbDrugCount.put("l-asparagine", "12");
		pgkbDrugCount.put("l-asparticacid", "9");
		pgkbDrugCount.put("l-carnitine", "8");
		pgkbDrugCount.put("l-citrulline", "10");
		pgkbDrugCount.put("l-cysteine", "17");
		pgkbDrugCount.put("l-cystine", "8");
		pgkbDrugCount.put("l-glutamicacid", "6");
		pgkbDrugCount.put("l-glutamine", "11");
		pgkbDrugCount.put("l-histidine", "7");
		pgkbDrugCount.put("l-isoleucine", "13");
		pgkbDrugCount.put("l-leucine", "6");
		pgkbDrugCount.put("l-lysine", "8");
		pgkbDrugCount.put("l-methionine", "7");
		pgkbDrugCount.put("l-methyldopa", "7");
		pgkbDrugCount.put("l-ornithine", "6");
		pgkbDrugCount.put("l-phenylalanine", "7");
		pgkbDrugCount.put("l-proline", "6");
		pgkbDrugCount.put("l-serine", "11");
		pgkbDrugCount.put("l-threonine", "6");
		pgkbDrugCount.put("l-tryptophan", "14");
		pgkbDrugCount.put("l-tyrosine", "8");
		pgkbDrugCount.put("l-valine", "13");
		pgkbDrugCount.put("labetalol", "6");
		pgkbDrugCount.put("lactose", "5");
		pgkbDrugCount.put("lactulose", "6");
		pgkbDrugCount.put("lamivudine", "52");
		pgkbDrugCount.put("lamotrigine", "82");
		pgkbDrugCount.put("lansoprazole", "34");
		pgkbDrugCount.put("lanthanumcarbonate", "1");
		pgkbDrugCount.put("lapatinib", "33");
		pgkbDrugCount.put("laronidase", "1");
		pgkbDrugCount.put("latamoxef", "13");
		pgkbDrugCount.put("latanoprost", "15");
		pgkbDrugCount.put("leflunomide", "31");
		pgkbDrugCount.put("lenalidomide", "13");
		pgkbDrugCount.put("lepirudin", "7");
		pgkbDrugCount.put("lercanidipine", "7");
		pgkbDrugCount.put("letrozole", "22");
		pgkbDrugCount.put("leucovorin", "88");
		pgkbDrugCount.put("leuprolide", "10");
		pgkbDrugCount.put("levallorphan", "4");
		pgkbDrugCount.put("levamisole", "6");
		pgkbDrugCount.put("levetiracetam", "15");
		pgkbDrugCount.put("levobunolol", "5");
		pgkbDrugCount.put("levobupivacaine", "9");
		pgkbDrugCount.put("levocabastine", "10");
		pgkbDrugCount.put("levodopa", "15");
		pgkbDrugCount.put("levofloxacin", "22");
		pgkbDrugCount.put("levomethadylacetate", "13");
		pgkbDrugCount.put("levonordefrin", "3");
		pgkbDrugCount.put("levonorgestrel", "11");
		pgkbDrugCount.put("levorphanol", "6");
		pgkbDrugCount.put("levosimendan", "8");
		pgkbDrugCount.put("levothyroxine", "11");
		pgkbDrugCount.put("lidocaine", "19");
		pgkbDrugCount.put("lidoflazine", "5");
		pgkbDrugCount.put("lincomycin", "8");
		pgkbDrugCount.put("lindane", "16");
		pgkbDrugCount.put("linezolid", "13");
		pgkbDrugCount.put("liothyronine", "21");
		pgkbDrugCount.put("liotrix", "12");
		pgkbDrugCount.put("lipoicacid", "6");
		pgkbDrugCount.put("liraglutide", "8");
		pgkbDrugCount.put("lisdexamfetamine", "9");
		pgkbDrugCount.put("lisinopril", "14");
		pgkbDrugCount.put("lisuride", "9");
		pgkbDrugCount.put("lithium", "1218");
		pgkbDrugCount.put("lofexidine", "5");
		pgkbDrugCount.put("lomefloxacin", "22");
		pgkbDrugCount.put("lomustine", "2");
		pgkbDrugCount.put("loperamide", "21");
		pgkbDrugCount.put("lopinavir", "18");
		pgkbDrugCount.put("loracarbef", "19");
		pgkbDrugCount.put("loratadine", "13");
		pgkbDrugCount.put("lorazepam", "8");
		pgkbDrugCount.put("Lorenzo'soil", "1");
		pgkbDrugCount.put("lornoxicam", "5");
		pgkbDrugCount.put("losartan", "35");
		pgkbDrugCount.put("loteprednoletabonate", "7");
		pgkbDrugCount.put("lovastatin", "53");
		pgkbDrugCount.put("loxapine", "4");
		pgkbDrugCount.put("lubiprostone", "13");
		pgkbDrugCount.put("lucanthone", "10");
		pgkbDrugCount.put("lumefantrine", "2");
		pgkbDrugCount.put("lumiracoxib", "12");
		pgkbDrugCount.put("lutropinalfa", "10");
		pgkbDrugCount.put("LY2140023", "6");
		pgkbDrugCount.put("lymecycline", "13");
		pgkbDrugCount.put("Magnesium", "2");
		pgkbDrugCount.put("magnesiumacetate", "2");
		pgkbDrugCount.put("magnesiumoxide", "4");
		pgkbDrugCount.put("magnesiumsulfate", "20");
		pgkbDrugCount.put("malathion", "7");
		pgkbDrugCount.put("mannitol", "11");
		pgkbDrugCount.put("maprotiline", "18");
		pgkbDrugCount.put("maraviroc", "22");
		pgkbDrugCount.put("marimastat", "5");
		pgkbDrugCount.put("marinol", "10");
		pgkbDrugCount.put("masoprocol", "10");
		pgkbDrugCount.put("mazindol", "7");
		pgkbDrugCount.put("Measlesvaccines", "1");
		pgkbDrugCount.put("mebendazole", "13");
		pgkbDrugCount.put("mecamylamine", "6");
		pgkbDrugCount.put("mecasermin", "25");
		pgkbDrugCount.put("mechlorethamine", "3");
		pgkbDrugCount.put("meclizine", "10");
		pgkbDrugCount.put("meclofenamicacid", "7");
		pgkbDrugCount.put("medroxyprogesterone", "13");
		pgkbDrugCount.put("medrysone", "7");
		pgkbDrugCount.put("mefenamicacid", "5");
		pgkbDrugCount.put("mefloquine", "16");
		pgkbDrugCount.put("megestrol", "9");
		pgkbDrugCount.put("melatonin", "17");
		pgkbDrugCount.put("meloxicam", "8");
		pgkbDrugCount.put("melphalan", "25");
		pgkbDrugCount.put("memantine", "9");
		pgkbDrugCount.put("menadione", "24");
		pgkbDrugCount.put("menotropins", "6");
		pgkbDrugCount.put("menthol", "25");
		pgkbDrugCount.put("mepenzolate", "13");
		pgkbDrugCount.put("meperidine", "13");
		pgkbDrugCount.put("mephentermine", "4");
		pgkbDrugCount.put("mephenytoin", "12");
		pgkbDrugCount.put("mepivacaine", "10");
		pgkbDrugCount.put("meprobamate", "12");
		pgkbDrugCount.put("mepyramine", "6");
		pgkbDrugCount.put("mequitazine", "9");
		pgkbDrugCount.put("mercaptopurine", "55");
		pgkbDrugCount.put("meropenem", "17");
		pgkbDrugCount.put("mesalazine", "8");
		pgkbDrugCount.put("mesoridazine", "7");
		pgkbDrugCount.put("mestranol", "9");
		pgkbDrugCount.put("metaraminol", "14");
		pgkbDrugCount.put("metaxalone", "8");
		pgkbDrugCount.put("metformin", "46");
		pgkbDrugCount.put("methacholine", "1");
		pgkbDrugCount.put("methacholinechloride", "7");
		pgkbDrugCount.put("methacycline", "10");
		pgkbDrugCount.put("methadone", "41");
		pgkbDrugCount.put("methadylacetate", "2");
		pgkbDrugCount.put("methamphetamine", "16");
		pgkbDrugCount.put("methantheline", "6");
		pgkbDrugCount.put("metharbital", "3");
		pgkbDrugCount.put("methazolamide", "4");
		pgkbDrugCount.put("methdilazine", "8");
		pgkbDrugCount.put("methimazole", "1");
		pgkbDrugCount.put("methocarbamol", "9");
		pgkbDrugCount.put("methohexital", "5");
		pgkbDrugCount.put("methotrexate", "196");
		pgkbDrugCount.put("methotrimeprazine", "11");
		pgkbDrugCount.put("methoxamine", "10");
		pgkbDrugCount.put("methoxsalen", "6");
		pgkbDrugCount.put("methoxyflurane", "9");
		pgkbDrugCount.put("methsuximide", "7");
		pgkbDrugCount.put("methyclothiazide", "10");
		pgkbDrugCount.put("methylaminolevulinate", "2");
		pgkbDrugCount.put("Methyldopa", "10");
		pgkbDrugCount.put("methyleneblue", "9");
		pgkbDrugCount.put("methylergonovine", "10");
		pgkbDrugCount.put("methylphenidate", "24");
		pgkbDrugCount.put("methylphenobarbital", "6");
		pgkbDrugCount.put("methylprednisolone", "14");
		pgkbDrugCount.put("methylscopolamine", "9");
		pgkbDrugCount.put("methyltestosterone", "5");
		pgkbDrugCount.put("methyprylon", "5");
		pgkbDrugCount.put("methysergide", "7");
		pgkbDrugCount.put("meticillin", "13");
		pgkbDrugCount.put("metipranolol", "4");
		pgkbDrugCount.put("metixene", "7");
		pgkbDrugCount.put("metoclopramide", "9");
		pgkbDrugCount.put("metocurine", "12");
		pgkbDrugCount.put("metolazone", "2");
		pgkbDrugCount.put("metoprolol", "24");
		pgkbDrugCount.put("metrizamide", "7");
		pgkbDrugCount.put("metronidazole", "11");
		pgkbDrugCount.put("metyrapone", "7");
		pgkbDrugCount.put("metyrosine", "7");
		pgkbDrugCount.put("mexiletine", "10");
		pgkbDrugCount.put("mezlocillin", "20");
		pgkbDrugCount.put("mianserin", "6");
		pgkbDrugCount.put("mibefradil", "15");
		pgkbDrugCount.put("micafungin", "18");
		pgkbDrugCount.put("miconazole", "9");
		pgkbDrugCount.put("midazolam", "28");
		pgkbDrugCount.put("midodrine", "7");
		pgkbDrugCount.put("mifepristone", "18");
		pgkbDrugCount.put("miglitol", "7");
		pgkbDrugCount.put("miglustat", "14");
		pgkbDrugCount.put("milnacipran", "14");
		pgkbDrugCount.put("milrinone", "8");
		pgkbDrugCount.put("mimosine", "7");
		pgkbDrugCount.put("minaprine", "2");
		pgkbDrugCount.put("minocycline", "10");
		pgkbDrugCount.put("minoxidil", "8");
		pgkbDrugCount.put("mirtazapine", "23");
		pgkbDrugCount.put("misoprostol", "9");
		pgkbDrugCount.put("mitiglinide", "8");
		pgkbDrugCount.put("mitomycin", "21");
		pgkbDrugCount.put("mitotane", "13");
		pgkbDrugCount.put("mitoxantrone", "24");
		pgkbDrugCount.put("mivacurium", "12");
		pgkbDrugCount.put("MK-7246", "10");
		pgkbDrugCount.put("moclobemide", "6");
		pgkbDrugCount.put("modafinil", "25");
		pgkbDrugCount.put("moexipril", "14");
		pgkbDrugCount.put("molindone", "4");
		pgkbDrugCount.put("mometasone", "4");
		pgkbDrugCount.put("MonoamineoxidaseBinhibitors", "5");
		pgkbDrugCount.put("monobenzone", "4");
		pgkbDrugCount.put("montelukast", "56");
		pgkbDrugCount.put("moricizine", "8");
		pgkbDrugCount.put("morphine", "35");
		pgkbDrugCount.put("moxifloxacin", "20");
		pgkbDrugCount.put("Multivitamins", "2");
		pgkbDrugCount.put("mupirocin", "4");
		pgkbDrugCount.put("muraglitazar", "6");
		pgkbDrugCount.put("mycophenolatemofetil", "32");
		pgkbDrugCount.put("mycophenolicacid", "48");
		pgkbDrugCount.put("n", "1");
		pgkbDrugCount.put("n-acetyl-4-benzoquinoneimine", "2");
		pgkbDrugCount.put("n-acetyl-4-benzosemiquinoneimine", "8");
		pgkbDrugCount.put("n-acetyl-d-glucosamine", "10");
		pgkbDrugCount.put("n-acetylserotonin", "3");
		pgkbDrugCount.put("nabilone", "10");
		pgkbDrugCount.put("nabumetone", "9");
		pgkbDrugCount.put("nadh", "7");
		pgkbDrugCount.put("nadolol", "8");
		pgkbDrugCount.put("nadroparin", "2");
		pgkbDrugCount.put("nafarelin", "11");
		pgkbDrugCount.put("nafcillin", "15");
		pgkbDrugCount.put("naftifine", "3");
		pgkbDrugCount.put("nalbuphine", "11");
		pgkbDrugCount.put("nalidixicacid", "7");
		pgkbDrugCount.put("naloxone", "8");
		pgkbDrugCount.put("naltrexone", "11");
		pgkbDrugCount.put("nandrolone", "9");
		pgkbDrugCount.put("nandrolonedecanoate", "7");
		pgkbDrugCount.put("naphazoline", "4");
		pgkbDrugCount.put("naproxen", "9");
		pgkbDrugCount.put("naratriptan", "3");
		pgkbDrugCount.put("naringenin", "8");
		pgkbDrugCount.put("natalizumab", "11");
		pgkbDrugCount.put("natamycin", "5");
		pgkbDrugCount.put("nateglinide", "17");
		pgkbDrugCount.put("nebivolol", "11");
		pgkbDrugCount.put("nedocromil", "6");
		pgkbDrugCount.put("nefazodone", "21");
		pgkbDrugCount.put("nelarabine", "10");
		pgkbDrugCount.put("nelfinavir", "21");
		pgkbDrugCount.put("nemonapride", "5");
		pgkbDrugCount.put("neomycin", "24");
		pgkbDrugCount.put("neostigmine", "3");
		pgkbDrugCount.put("nepafenac", "3");
		pgkbDrugCount.put("nesiritide", "9");
		pgkbDrugCount.put("netilmicin", "8");
		pgkbDrugCount.put("nevirapine", "84");
		pgkbDrugCount.put("NG-monomethyl-l-arginine", "1");
		pgkbDrugCount.put("niacin", "19");
		pgkbDrugCount.put("nicardipine", "25");
		pgkbDrugCount.put("nicergoline", "8");
		pgkbDrugCount.put("niclosamide", "4");
		pgkbDrugCount.put("nicotine", "71");
		pgkbDrugCount.put("nifedipine", "33");
		pgkbDrugCount.put("niflumicacid", "9");
		pgkbDrugCount.put("nilotinib", "25");
		pgkbDrugCount.put("nilutamide", "9");
		pgkbDrugCount.put("nilvadipine", "3");
		pgkbDrugCount.put("nimesulide", "7");
		pgkbDrugCount.put("nimodipine", "10");
		pgkbDrugCount.put("nisoldipine", "8");
		pgkbDrugCount.put("nitazoxanide", "3");
		pgkbDrugCount.put("nitisinone", "17");
		pgkbDrugCount.put("nitrazepam", "4");
		pgkbDrugCount.put("nitrendipine", "16");
		pgkbDrugCount.put("nitricoxide", "4");
		pgkbDrugCount.put("nitrofurantoin", "13");
		pgkbDrugCount.put("nitrofurazone", "10");
		pgkbDrugCount.put("nitroglycerin", "5");
		pgkbDrugCount.put("nitroprusside", "4");
		pgkbDrugCount.put("nitrousoxide", "7");
		pgkbDrugCount.put("nitroxoline", "3");
		pgkbDrugCount.put("nizatidine", "9");
		pgkbDrugCount.put("non-selectivemonoaminereuptakeinhibitors", "3");
		pgkbDrugCount.put("Non-watersolubleX-raycontrastmedia", "1");
		pgkbDrugCount.put("nonoxynol-9", "7");
		pgkbDrugCount.put("norelgestromin", "1");
		pgkbDrugCount.put("norepinephrine", "21");
		pgkbDrugCount.put("norethindrone", "10");
		pgkbDrugCount.put("norfloxacin", "11");
		pgkbDrugCount.put("norgestimate", "5");
		pgkbDrugCount.put("norgestrel", "6");
		pgkbDrugCount.put("nortriptyline", "35");
		pgkbDrugCount.put("novobiocin", "17");
		pgkbDrugCount.put("null", "246");
		pgkbDrugCount.put("nystatin", "9");
		pgkbDrugCount.put("OC144-093", "8");
		pgkbDrugCount.put("octreotide", "13");
		pgkbDrugCount.put("ofloxacin", "13");
		pgkbDrugCount.put("olanzapine", "98");
		pgkbDrugCount.put("olmesartan", "10");
		pgkbDrugCount.put("olopatadine", "7");
		pgkbDrugCount.put("olsalazine", "11");
		pgkbDrugCount.put("omalizumab", "3");
		pgkbDrugCount.put("omega-3polyunsaturatedfattyacids", "4");
		pgkbDrugCount.put("omeprazole", "30");
		pgkbDrugCount.put("ondansetron", "27");
		pgkbDrugCount.put("opioids", "5");
		pgkbDrugCount.put("Opipramol", "5");
		pgkbDrugCount.put("Opiumalkaloidsandderivatives", "3");
		pgkbDrugCount.put("oprelvekin", "4");
		pgkbDrugCount.put("orciprenaline", "7");
		pgkbDrugCount.put("orlistat", "11");
		pgkbDrugCount.put("orphenadrine", "6");
		pgkbDrugCount.put("oseltamivir", "16");
		pgkbDrugCount.put("ospalipoprotein", "5");
		pgkbDrugCount.put("Otherantidepressants", "8");
		pgkbDrugCount.put("OtherclassIantiarrhythmics", "1");
		pgkbDrugCount.put("Otheri.v.solutionadditives", "3");
		pgkbDrugCount.put("ouabain", "13");
		pgkbDrugCount.put("oxacillin", "6");
		pgkbDrugCount.put("oxaliplatin", "55");
		pgkbDrugCount.put("oxamniquine", "1");
		pgkbDrugCount.put("oxandrolone", "6");
		pgkbDrugCount.put("oxaprozin", "3");
		pgkbDrugCount.put("oxatomide", "2");
		pgkbDrugCount.put("oxazepam", "13");
		pgkbDrugCount.put("oxcarbazepine", "14");
		pgkbDrugCount.put("oxiconazole", "15");
		pgkbDrugCount.put("oxprenolol", "1");
		pgkbDrugCount.put("oxtriphylline", "5");
		pgkbDrugCount.put("oxybenzone", "2");
		pgkbDrugCount.put("oxybuprocaine", "3");
		pgkbDrugCount.put("oxybutynin", "4");
		pgkbDrugCount.put("oxycodone", "25");
		pgkbDrugCount.put("oxygen", "1");
		pgkbDrugCount.put("oxymetazoline", "3");
		pgkbDrugCount.put("oxymorphone", "5");
		pgkbDrugCount.put("oxyphenbutazone", "3");
		pgkbDrugCount.put("oxyphencyclimine", "3");
		pgkbDrugCount.put("oxyphenonium", "2");
		pgkbDrugCount.put("oxytetracycline", "7");
		pgkbDrugCount.put("oxytocin", "7");
		pgkbDrugCount.put("paclitaxel", "109");
		pgkbDrugCount.put("pactimibe", "1");
		pgkbDrugCount.put("palifermin", "13");
		pgkbDrugCount.put("paliperidone", "21");
		pgkbDrugCount.put("palivizumab", "5");
		pgkbDrugCount.put("palonosetron", "19");
		pgkbDrugCount.put("pamidronate", "17");
		pgkbDrugCount.put("pancrelipase", "2");
		pgkbDrugCount.put("pancuronium", "7");
		pgkbDrugCount.put("panitumumab", "31");
		pgkbDrugCount.put("pantoprazole", "40");
		pgkbDrugCount.put("papaverine", "7");
		pgkbDrugCount.put("paramethadione", "4");
		pgkbDrugCount.put("paramethasone", "24");
		pgkbDrugCount.put("pargyline", "8");
		pgkbDrugCount.put("paricalcitol", "27");
		pgkbDrugCount.put("paromomycin", "8");
		pgkbDrugCount.put("paroxetine", "56");
		pgkbDrugCount.put("pefloxacin", "15");
		pgkbDrugCount.put("pegaspargase", "3");
		pgkbDrugCount.put("pegfilgrastim", "2");
		pgkbDrugCount.put("peginterferonalfa-2a", "22");
		pgkbDrugCount.put("peginterferonalfa-2b", "33");
		pgkbDrugCount.put("pegloticase", "4");
		pgkbDrugCount.put("pegvisomant", "7");
		pgkbDrugCount.put("pemetrexed", "36");
		pgkbDrugCount.put("pemirolast", "16");
		pgkbDrugCount.put("pemoline", "5");
		pgkbDrugCount.put("penbutolol", "7");
		pgkbDrugCount.put("penciclovir", "3");
		pgkbDrugCount.put("penicillamine", "13");
		pgkbDrugCount.put("penicilling", "17");
		pgkbDrugCount.put("penicillinv", "40");
		pgkbDrugCount.put("pentagastrin", "13");
		pgkbDrugCount.put("pentamidine", "10");
		pgkbDrugCount.put("pentazocine", "13");
		pgkbDrugCount.put("pentobarbital", "3");
		pgkbDrugCount.put("pentosanpolysulfate", "38");
		pgkbDrugCount.put("pentostatin", "14");
		pgkbDrugCount.put("pentoxifylline", "5");
		pgkbDrugCount.put("perflutren", "5");
		pgkbDrugCount.put("pergolide", "16");
		pgkbDrugCount.put("perhexiline", "10");
		pgkbDrugCount.put("perindopril", "14");
		pgkbDrugCount.put("permethrin", "13");
		pgkbDrugCount.put("perphenazine", "32");
		pgkbDrugCount.put("Pertuzumab", "8");
		pgkbDrugCount.put("phenacemide", "2");
		pgkbDrugCount.put("phenacetin", "8");
		pgkbDrugCount.put("phenazopyridine", "2");
		pgkbDrugCount.put("phencyclidine", "6");
		pgkbDrugCount.put("phendimetrazine", "4");
		pgkbDrugCount.put("phenelzine", "9");
		pgkbDrugCount.put("phenformin", "7");
		pgkbDrugCount.put("phenindamine", "6");
		pgkbDrugCount.put("phenindione", "7");
		pgkbDrugCount.put("pheniramine", "3");
		pgkbDrugCount.put("phenmetrazine", "5");
		pgkbDrugCount.put("phenobarbital", "25");
		pgkbDrugCount.put("phenol", "5");
		pgkbDrugCount.put("Phenothiazinederivatives", "8");
		pgkbDrugCount.put("phenothiazineswithaliphaticside-chain", "8");
		pgkbDrugCount.put("phenoxybenzamine", "4");
		pgkbDrugCount.put("phenprocoumon", "34");
		pgkbDrugCount.put("phensuximide", "5");
		pgkbDrugCount.put("phentermine", "2");
		pgkbDrugCount.put("phentolamine", "5");
		pgkbDrugCount.put("phenylaceticacid", "13");
		pgkbDrugCount.put("phenylbutazone", "18");
		pgkbDrugCount.put("phenylephrine", "16");
		pgkbDrugCount.put("phenylpropanolamine", "10");
		pgkbDrugCount.put("phenytoin", "80");
		pgkbDrugCount.put("phosphatidylserine", "8");
		pgkbDrugCount.put("phosphoricacid", "2");
		pgkbDrugCount.put("photodynamictherapy", "32");
		pgkbDrugCount.put("physostigmine", "8");
		pgkbDrugCount.put("phytonadione", "10");
		pgkbDrugCount.put("picrotoxin", "11");
		pgkbDrugCount.put("pilocarpine", "12");
		pgkbDrugCount.put("pimecrolimus", "10");
		pgkbDrugCount.put("pimozide", "23");
		pgkbDrugCount.put("pindolol", "3");
		pgkbDrugCount.put("pioglitazone", "16");
		pgkbDrugCount.put("pipazethate", "3");
		pgkbDrugCount.put("pipecuronium", "4");
		pgkbDrugCount.put("piperacillin", "12");
		pgkbDrugCount.put("piperazine", "9");
		pgkbDrugCount.put("pipobroman", "1");
		pgkbDrugCount.put("pipotiazine", "3");
		pgkbDrugCount.put("pirbuterol", "6");
		pgkbDrugCount.put("pirenzepine", "4");
		pgkbDrugCount.put("piroxicam", "7");
		pgkbDrugCount.put("pitavastatin", "11");
		pgkbDrugCount.put("pitrakinra", "7");
		pgkbDrugCount.put("pivampicillin", "11");
		pgkbDrugCount.put("pivmecillinam", "6");
		pgkbDrugCount.put("platinum", "37");
		pgkbDrugCount.put("Platinumcompounds", "50");
		pgkbDrugCount.put("plerixafor", "10");
		pgkbDrugCount.put("plicamycin", "7");
		pgkbDrugCount.put("podofilox", "8");
		pgkbDrugCount.put("polymyxinbsulfate", "15");
		pgkbDrugCount.put("polystyrenesulfonate", "4");
		pgkbDrugCount.put("polythiazide", "10");
		pgkbDrugCount.put("porfimer", "15");
		pgkbDrugCount.put("posaconazole", "17");
		pgkbDrugCount.put("potassium", "1");
		pgkbDrugCount.put("potassiumacetate", "3");
		pgkbDrugCount.put("potassiumchloride", "40");
		pgkbDrugCount.put("potassiumiodide", "3");
		pgkbDrugCount.put("practolol", "3");
		pgkbDrugCount.put("pralidoxime", "2");
		pgkbDrugCount.put("pramipexole", "10");
		pgkbDrugCount.put("pramlintide", "4");
		pgkbDrugCount.put("pranlukast", "9");
		pgkbDrugCount.put("prasugrel", "15");
		pgkbDrugCount.put("pravastatin", "78");
		pgkbDrugCount.put("prazepam", "8");
		pgkbDrugCount.put("praziquantel", "12");
		pgkbDrugCount.put("prazosin", "11");
		pgkbDrugCount.put("prednicarbate", "8");
		pgkbDrugCount.put("prednisolone", "34");
		pgkbDrugCount.put("prednisone", "30");
		pgkbDrugCount.put("pregabalin", "10");
		pgkbDrugCount.put("prenylamine", "5");
		pgkbDrugCount.put("preotact", "9");
		pgkbDrugCount.put("prilocaine", "1");
		pgkbDrugCount.put("primaquine", "14");
		pgkbDrugCount.put("primidone", "6");
		pgkbDrugCount.put("probenecid", "14");
		pgkbDrugCount.put("probucol", "11");
		pgkbDrugCount.put("procainamide", "16");
		pgkbDrugCount.put("procaine", "8");
		pgkbDrugCount.put("procarbazine", "6");
		pgkbDrugCount.put("procaterol", "5");
		pgkbDrugCount.put("prochlorperazine", "6");
		pgkbDrugCount.put("procyclidine", "5");
		pgkbDrugCount.put("proflavine", "9");
		pgkbDrugCount.put("progabide", "13");
		pgkbDrugCount.put("progesterone", "26");
		pgkbDrugCount.put("proguanil", "3");
		pgkbDrugCount.put("promazine", "4");
		pgkbDrugCount.put("promethazine", "8");
		pgkbDrugCount.put("propafenone", "33");
		pgkbDrugCount.put("propantheline", "5");
		pgkbDrugCount.put("proparacaine", "2");
		pgkbDrugCount.put("propericiazine", "5");
		pgkbDrugCount.put("propiomazine", "9");
		pgkbDrugCount.put("propofol", "15");
		pgkbDrugCount.put("propoxyphene", "10");
		pgkbDrugCount.put("propranolol", "23");
		pgkbDrugCount.put("propylhexedrine", "1");
		pgkbDrugCount.put("propylthiouracil", "2");
		pgkbDrugCount.put("proteaseinhibitors", "11");
		pgkbDrugCount.put("Protonpumpinhibitors", "2");
		pgkbDrugCount.put("protriptyline", "11");
		pgkbDrugCount.put("pseudoephedrine", "13");
		pgkbDrugCount.put("purineanalogues", "7");
		pgkbDrugCount.put("pyrazinamide", "31");
		pgkbDrugCount.put("pyrethrinsandpiperonylbutoxide", "2");
		pgkbDrugCount.put("pyridostigmine", "2");
		pgkbDrugCount.put("pyridoxal", "6");
		pgkbDrugCount.put("pyridoxalphosphate", "8");
		pgkbDrugCount.put("pyridoxine", "12");
		pgkbDrugCount.put("pyrilamine", "8");
		pgkbDrugCount.put("pyrimethamine", "12");
		pgkbDrugCount.put("Pyrimidineanalogues", "8");
		pgkbDrugCount.put("pyruvicacid", "3");
		pgkbDrugCount.put("quazepam", "9");
		pgkbDrugCount.put("quetiapine", "33");
		pgkbDrugCount.put("quinacrine", "3");
		pgkbDrugCount.put("quinapril", "12");
		pgkbDrugCount.put("quinestrol", "5");
		pgkbDrugCount.put("quinethazone", "7");
		pgkbDrugCount.put("quinidine", "40");
		pgkbDrugCount.put("quinidinebarbiturate", "2");
		pgkbDrugCount.put("quinine", "19");
		pgkbDrugCount.put("quinupristin", "17");
		pgkbDrugCount.put("R101933", "8");
		pgkbDrugCount.put("rabeprazole", "16");
		pgkbDrugCount.put("raloxifene", "17");
		pgkbDrugCount.put("raltitrexed", "23");
		pgkbDrugCount.put("ramelteon", "46");
		pgkbDrugCount.put("ramipril", "12");
		pgkbDrugCount.put("ranibizumab", "39");
		pgkbDrugCount.put("ranitidine", "25");
		pgkbDrugCount.put("ranolazine", "5");
		pgkbDrugCount.put("rasagiline", "14");
		pgkbDrugCount.put("rasburicase", "6");
		pgkbDrugCount.put("reboxetine", "7");
		pgkbDrugCount.put("remifentanil", "4");
		pgkbDrugCount.put("remikiren", "6");
		pgkbDrugCount.put("remoxipride", "5");
		pgkbDrugCount.put("repaglinide", "54");
		pgkbDrugCount.put("rescinnamine", "16");
		pgkbDrugCount.put("reserpine", "21");
		pgkbDrugCount.put("Resveratrol", "7");
		pgkbDrugCount.put("retapamulin", "9");
		pgkbDrugCount.put("reteplase", "1");
		pgkbDrugCount.put("retigabine", "6");
		pgkbDrugCount.put("rhodamine123", "8");
		pgkbDrugCount.put("ribavirin", "71");
		pgkbDrugCount.put("riboflavin", "13");
		pgkbDrugCount.put("ridogrel", "9");
		pgkbDrugCount.put("rifabutin", "15");
		pgkbDrugCount.put("rifampin", "58");
		pgkbDrugCount.put("rifapentine", "15");
		pgkbDrugCount.put("rifaximin", "23");
		pgkbDrugCount.put("riluzole", "15");
		pgkbDrugCount.put("rimantadine", "13");
		pgkbDrugCount.put("rimexolone", "9");
		pgkbDrugCount.put("rimonabant", "11");
		pgkbDrugCount.put("risedronate", "23");
		pgkbDrugCount.put("risperidone", "98");
		pgkbDrugCount.put("ritodrine", "12");
		pgkbDrugCount.put("ritonavir", "39");
		pgkbDrugCount.put("rituximab", "22");
		pgkbDrugCount.put("rivaroxaban", "6");
		pgkbDrugCount.put("rivastigmine", "8");
		pgkbDrugCount.put("rizatriptan", "4");
		pgkbDrugCount.put("rocuronium", "10");
		pgkbDrugCount.put("rofecoxib", "10");
		pgkbDrugCount.put("roflumilast", "8");
		pgkbDrugCount.put("rolipram", "5");
		pgkbDrugCount.put("rolitetracycline", "10");
		pgkbDrugCount.put("ropinirole", "10");
		pgkbDrugCount.put("ropivacaine", "9");
		pgkbDrugCount.put("rosiglitazone", "27");
		pgkbDrugCount.put("rosoxacin", "7");
		pgkbDrugCount.put("rosuvastatin", "59");
		pgkbDrugCount.put("rotigotinetransdermalpatch", "2");
		pgkbDrugCount.put("roxatidineacetate", "5");
		pgkbDrugCount.put("roxithromycin", "4");
		pgkbDrugCount.put("s-adenosylmethionine", "11");
		pgkbDrugCount.put("salbutamol", "33");
		pgkbDrugCount.put("salicyclicacid", "7");
		pgkbDrugCount.put("salicylamide", "2");
		pgkbDrugCount.put("salicylate-magnesium", "4");
		pgkbDrugCount.put("salicylate-sodium", "3");
		pgkbDrugCount.put("salmeterol", "9");
		pgkbDrugCount.put("salmoncalcitonin", "8");
		pgkbDrugCount.put("salsalate", "3");
		pgkbDrugCount.put("saprisartan", "14");
		pgkbDrugCount.put("saquinavir", "28");
		pgkbDrugCount.put("sargramostim", "4");
		pgkbDrugCount.put("satumomabpendetide", "3");
		pgkbDrugCount.put("saxagliptin", "8");
		pgkbDrugCount.put("scopolamine", "11");
		pgkbDrugCount.put("secobarbital", "5");
		pgkbDrugCount.put("secretin", "2");
		pgkbDrugCount.put("selectivebeta-2-adrenoreceptoragonists", "13");
		pgkbDrugCount.put("Selectiveserotoninreuptakeinhibitors", "19");
		pgkbDrugCount.put("selegiline", "24");
		pgkbDrugCount.put("seleniumsulfide", "8");
		pgkbDrugCount.put("sematilide", "3");
		pgkbDrugCount.put("sermorelin", "12");
		pgkbDrugCount.put("sertaconazole", "8");
		pgkbDrugCount.put("sertindole", "16");
		pgkbDrugCount.put("sertraline", "30");
		pgkbDrugCount.put("serumalbuminiodonated", "1");
		pgkbDrugCount.put("sevelamer", "6");
		pgkbDrugCount.put("sevoflurane", "13");
		pgkbDrugCount.put("sibutramine", "19");
		pgkbDrugCount.put("sildenafil", "18");
		pgkbDrugCount.put("silvernitrate", "1");
		pgkbDrugCount.put("silversulfadiazine", "5");
		pgkbDrugCount.put("simvastatin", "75");
		pgkbDrugCount.put("sirolimus", "60");
		pgkbDrugCount.put("sitagliptin", "19");
		pgkbDrugCount.put("sitaxentan", "13");
		pgkbDrugCount.put("SN-38", "37");
		pgkbDrugCount.put("sodiumacetate", "2");
		pgkbDrugCount.put("sodiumbenzoate", "12");
		pgkbDrugCount.put("sodiumbicarbonate", "12");
		pgkbDrugCount.put("sodiumbisulfite", "2");
		pgkbDrugCount.put("sodiumcarbonate", "2");
		pgkbDrugCount.put("sodiumchloride", "1");
		pgkbDrugCount.put("sodiumcitrate", "2");
		pgkbDrugCount.put("sodiumfluoride", "6");
		pgkbDrugCount.put("sodiumlaurylsulfate", "26");
		pgkbDrugCount.put("sodiumphosphate", "2");
		pgkbDrugCount.put("sodiumselenite", "2");
		pgkbDrugCount.put("sodiumstibogluconate", "3");
		pgkbDrugCount.put("sodiumsuccinate", "2");
		pgkbDrugCount.put("sodiumsulfate", "3");
		pgkbDrugCount.put("sodiumtetradecylsulfate", "6");
		pgkbDrugCount.put("solifenacin", "13");
		pgkbDrugCount.put("somatropinrecombinant", "29");
		pgkbDrugCount.put("sorafenib", "112");
		pgkbDrugCount.put("sotalol", "26");
		pgkbDrugCount.put("sparfloxacin", "23");
		pgkbDrugCount.put("sparteine", "4");
		pgkbDrugCount.put("spectinomycin", "6");
		pgkbDrugCount.put("spirapril", "9");
		pgkbDrugCount.put("spironolactone", "26");
		pgkbDrugCount.put("st.john'swort", "13");
		pgkbDrugCount.put("stanozolol", "2");
		pgkbDrugCount.put("staurosporine", "5");
		pgkbDrugCount.put("stavudine", "16");
		pgkbDrugCount.put("stepronin", "2");
		pgkbDrugCount.put("streptomycin", "23");
		pgkbDrugCount.put("streptozocin", "4");
		pgkbDrugCount.put("succimer", "8");
		pgkbDrugCount.put("succinicacid", "3");
		pgkbDrugCount.put("succinylcholine", "2");
		pgkbDrugCount.put("sucralfate", "9");
		pgkbDrugCount.put("sucrose", "4");
		pgkbDrugCount.put("sufentanil", "9");
		pgkbDrugCount.put("sulfacetamide", "5");
		pgkbDrugCount.put("sulfacytine", "4");
		pgkbDrugCount.put("sulfadiazine", "7");
		pgkbDrugCount.put("sulfadimethoxine", "4");
		pgkbDrugCount.put("sulfadoxine", "12");
		pgkbDrugCount.put("sulfamerazine", "5");
		pgkbDrugCount.put("sulfamethazine", "8");
		pgkbDrugCount.put("sulfamethizole", "3");
		pgkbDrugCount.put("sulfamethoxazole", "23");
		pgkbDrugCount.put("sulfamethoxazoleandtrimethoprim", "1");
		pgkbDrugCount.put("sulfametopyrazine", "5");
		pgkbDrugCount.put("sulfamoxole", "3");
		pgkbDrugCount.put("sulfanilamide", "9");
		pgkbDrugCount.put("sulfaphenazole", "4");
		pgkbDrugCount.put("sulfapyridine", "11");
		pgkbDrugCount.put("sulfasalazine", "40");
		pgkbDrugCount.put("sulfathiazole", "4");
		pgkbDrugCount.put("sulfinpyrazone", "13");
		pgkbDrugCount.put("sulfisoxazole", "14");
		pgkbDrugCount.put("sulfonamides", "33");
		pgkbDrugCount.put("sulfoxone", "5");
		pgkbDrugCount.put("sulfur", "1");
		pgkbDrugCount.put("sulindac", "12");
		pgkbDrugCount.put("sulodexide", "3");
		pgkbDrugCount.put("sulpiride", "4");
		pgkbDrugCount.put("sumatriptan", "9");
		pgkbDrugCount.put("sunitinib", "45");
		pgkbDrugCount.put("suprofen", "5");
		pgkbDrugCount.put("suramin", "12");
		pgkbDrugCount.put("tacrine", "3");
		pgkbDrugCount.put("tacrolimus", "60");
		pgkbDrugCount.put("tadalafil", "14");
		pgkbDrugCount.put("tafluprost", "8");
		pgkbDrugCount.put("talbutal", "5");
		pgkbDrugCount.put("talinolol", "10");
		pgkbDrugCount.put("tamibarotene", "7");
		pgkbDrugCount.put("tamoxifen", "99");
		pgkbDrugCount.put("tamsulosin", "9");
		pgkbDrugCount.put("Tariquidar", "8");
		pgkbDrugCount.put("tasosartan", "8");
		pgkbDrugCount.put("taurine", "2");
		pgkbDrugCount.put("taxanes", "16");
		pgkbDrugCount.put("tazarotene", "8");
		pgkbDrugCount.put("tazobactam", "4");
		pgkbDrugCount.put("tegafur", "24");
		pgkbDrugCount.put("tegaserod", "11");
		pgkbDrugCount.put("teicoplanin", "15");
		pgkbDrugCount.put("telaprevir", "22");
		pgkbDrugCount.put("telatinib", "8");
		pgkbDrugCount.put("telbivudine", "10");
		pgkbDrugCount.put("telithromycin", "18");
		pgkbDrugCount.put("telmisartan", "14");
		pgkbDrugCount.put("temazepam", "13");
		pgkbDrugCount.put("temozolomide", "9");
		pgkbDrugCount.put("temsirolimus", "34");
		pgkbDrugCount.put("teniposide", "19");
		pgkbDrugCount.put("tenofovir", "43");
		pgkbDrugCount.put("tenoxicam", "4");
		pgkbDrugCount.put("terazosin", "6");
		pgkbDrugCount.put("terbinafine", "13");
		pgkbDrugCount.put("terbutaline", "5");
		pgkbDrugCount.put("terconazole", "9");
		pgkbDrugCount.put("terfenadine", "28");
		pgkbDrugCount.put("teriparatide", "9");
		pgkbDrugCount.put("terlipressin", "12");
		pgkbDrugCount.put("terodiline", "9");
		pgkbDrugCount.put("testolactone", "7");
		pgkbDrugCount.put("testosterone", "38");
		pgkbDrugCount.put("testosteronepropionate", "6");
		pgkbDrugCount.put("tetrabenazine", "16");
		pgkbDrugCount.put("tetracaine", "1");
		pgkbDrugCount.put("tetracycline", "29");
		pgkbDrugCount.put("tetrahydrobiopterin", "19");
		pgkbDrugCount.put("tetrahydrofolicacid", "12");
		pgkbDrugCount.put("thalidomide", "48");
		pgkbDrugCount.put("theobromine", "3");
		pgkbDrugCount.put("theophylline", "18");
		pgkbDrugCount.put("thiabendazole", "9");
		pgkbDrugCount.put("thiamine", "57");
		pgkbDrugCount.put("thiamylal", "7");
		pgkbDrugCount.put("Thiazides", "40");
		pgkbDrugCount.put("thiazolidinediones", "2");
		pgkbDrugCount.put("thiethylperazine", "4");
		pgkbDrugCount.put("thioguanine", "16");
		pgkbDrugCount.put("thiopental", "6");
		pgkbDrugCount.put("thioproperazine", "7");
		pgkbDrugCount.put("thioridazine", "14");
		pgkbDrugCount.put("thiotepa", "13");
		pgkbDrugCount.put("thiothixene", "10");
		pgkbDrugCount.put("thymalfasin", "21");
		pgkbDrugCount.put("thyroglobulin", "1");
		pgkbDrugCount.put("thyroidhormones", "4");
		pgkbDrugCount.put("thyrotropinalfa", "33");
		pgkbDrugCount.put("thyroxine", "4");
		pgkbDrugCount.put("tiagabine", "7");
		pgkbDrugCount.put("tiaprofenicacid", "8");
		pgkbDrugCount.put("ticagrelor", "3");
		pgkbDrugCount.put("ticarcillin", "10");
		pgkbDrugCount.put("ticlopidine", "16");
		pgkbDrugCount.put("tigecycline", "27");
		pgkbDrugCount.put("tiludronate", "6");
		pgkbDrugCount.put("timolol", "15");
		pgkbDrugCount.put("tinidazole", "10");
		pgkbDrugCount.put("tinzaparin", "3");
		pgkbDrugCount.put("tioconazole", "13");
		pgkbDrugCount.put("tiotropium", "18");
		pgkbDrugCount.put("tipifarnib", "45");
		pgkbDrugCount.put("tipranavir", "15");
		pgkbDrugCount.put("tirofiban", "48");
		pgkbDrugCount.put("tizanidine", "4");
		pgkbDrugCount.put("tobramycin", "13");
		pgkbDrugCount.put("tocainide", "4");
		pgkbDrugCount.put("tofisopam", "9");
		pgkbDrugCount.put("tolazamide", "3");
		pgkbDrugCount.put("tolazoline", "2");
		pgkbDrugCount.put("tolbutamide", "15");
		pgkbDrugCount.put("tolcapone", "11");
		pgkbDrugCount.put("tolmetin", "4");
		pgkbDrugCount.put("tolnaftate", "8");
		pgkbDrugCount.put("tolterodine", "17");
		pgkbDrugCount.put("topiramate", "18");
		pgkbDrugCount.put("topoisomeraseIinhibitors", "7");
		pgkbDrugCount.put("topotecan", "14");
		pgkbDrugCount.put("torasemide", "13");
		pgkbDrugCount.put("toremifene", "11");
		pgkbDrugCount.put("tositumomab", "16");
		pgkbDrugCount.put("trabectedin", "10");
		pgkbDrugCount.put("tramadol", "37");
		pgkbDrugCount.put("trandolapril", "18");
		pgkbDrugCount.put("tranexamicacid", "13");
		pgkbDrugCount.put("tranilast", "4");
		pgkbDrugCount.put("tranylcypromine", "15");
		pgkbDrugCount.put("trastuzumab", "43");
		pgkbDrugCount.put("trastuzumabemtansine", "14");
		pgkbDrugCount.put("travoprost", "14");
		pgkbDrugCount.put("trazodone", "16");
		pgkbDrugCount.put("treprostinil", "14");
		pgkbDrugCount.put("tretinoin", "11");
		pgkbDrugCount.put("triamcinolone", "12");
		pgkbDrugCount.put("triamterene", "15");
		pgkbDrugCount.put("triazolam", "10");
		pgkbDrugCount.put("trichlormethiazide", "12");
		pgkbDrugCount.put("trichloroaceticacid", "1");
		pgkbDrugCount.put("tridihexethyl", "4");
		pgkbDrugCount.put("trifluoperazine", "10");
		pgkbDrugCount.put("triflupromazine", "11");
		pgkbDrugCount.put("trifluridine", "14");
		pgkbDrugCount.put("triflusal", "12");
		pgkbDrugCount.put("trihexyphenidyl", "6");
		pgkbDrugCount.put("trilostane", "7");
		pgkbDrugCount.put("trimeprazine", "11");
		pgkbDrugCount.put("trimethadione", "3");
		pgkbDrugCount.put("trimethaphan", "11");
		pgkbDrugCount.put("trimethobenzamide", "6");
		pgkbDrugCount.put("trimethoprim", "8");
		pgkbDrugCount.put("trimetrexate", "8");
		pgkbDrugCount.put("trimipramine", "14");
		pgkbDrugCount.put("trioxsalen", "1");
		pgkbDrugCount.put("tripelennamine", "4");
		pgkbDrugCount.put("triprolidine", "3");
		pgkbDrugCount.put("trisalicylate-choline", "4");
		pgkbDrugCount.put("troglitazone", "21");
		pgkbDrugCount.put("troleandomycin", "11");
		pgkbDrugCount.put("tropicamide", "8");
		pgkbDrugCount.put("trospium", "9");
		pgkbDrugCount.put("trovafloxacin", "14");
		pgkbDrugCount.put("tubercidin", "8");
		pgkbDrugCount.put("tubocurarine", "12");
		pgkbDrugCount.put("Tumornecrosisfactoralpha(TNF-alpha)inhibitors", "79");
		pgkbDrugCount.put("tyloxapol", "5");
		pgkbDrugCount.put("tymazoline", "3");
		pgkbDrugCount.put("udenafil", "17");
		pgkbDrugCount.put("uracilmustard", "3");
		pgkbDrugCount.put("urea", "1");
		pgkbDrugCount.put("uridine", "5");
		pgkbDrugCount.put("urofollitropin", "40");
		pgkbDrugCount.put("urokinase", "8");
		pgkbDrugCount.put("ursodeoxycholicacid", "7");
		pgkbDrugCount.put("valaciclovir", "8");
		pgkbDrugCount.put("valdecoxib", "13");
		pgkbDrugCount.put("valganciclovir", "9");
		pgkbDrugCount.put("valinomycin", "8");
		pgkbDrugCount.put("valproicacid", "113");
		pgkbDrugCount.put("valrubicin", "18");
		pgkbDrugCount.put("valsartan", "14");
		pgkbDrugCount.put("Valspodar", "8");
		pgkbDrugCount.put("vancomycin", "12");
		pgkbDrugCount.put("vanillin", "1");
		pgkbDrugCount.put("vapreotide", "12");
		pgkbDrugCount.put("vardenafil", "14");
		pgkbDrugCount.put("varenicline", "8");
		pgkbDrugCount.put("vasopressin", "8");
		pgkbDrugCount.put("vatalanib", "1");
		pgkbDrugCount.put("vecuronium", "12");
		pgkbDrugCount.put("vemurafenib", "20");
		pgkbDrugCount.put("venlafaxine", "46");
		pgkbDrugCount.put("verapamil", "52");
		pgkbDrugCount.put("verteporfin", "13");
		pgkbDrugCount.put("vidarabine", "13");
		pgkbDrugCount.put("vigabatrin", "6");
		pgkbDrugCount.put("vilazodone", "7");
		pgkbDrugCount.put("vildagliptin", "15");
		pgkbDrugCount.put("vinblastine", "26");
		pgkbDrugCount.put("vincristine", "59");
		pgkbDrugCount.put("vindesine", "14");
		pgkbDrugCount.put("vinorelbine", "23");
		pgkbDrugCount.put("vitamina", "29");
		pgkbDrugCount.put("VitaminAAndD", "2");
		pgkbDrugCount.put("VitaminAandDincombination", "2");
		pgkbDrugCount.put("vitaminb-complex", "20");
		pgkbDrugCount.put("VitaminB-complexwithanabolicsteroids", "5");
		pgkbDrugCount.put("VitaminB-complexwithminerals", "5");
		pgkbDrugCount.put("VitaminB-complexwithvitaminC", "5");
		pgkbDrugCount.put("VitaminB1", "2");
		pgkbDrugCount.put("VitaminB1incombinationwithvitaminB6and/orvitaminB12", "1");
		pgkbDrugCount.put("vitaminc", "20");
		pgkbDrugCount.put("vitamindandanalogues", "4");
		pgkbDrugCount.put("vitamine", "21");
		pgkbDrugCount.put("VitaminK", "1");
		pgkbDrugCount.put("VitaminKAndOtherHemostatics", "1");
		pgkbDrugCount.put("VitaminKantagonists", "1");
		pgkbDrugCount.put("voacamine", "4");
		pgkbDrugCount.put("voglibose", "6");
		pgkbDrugCount.put("voriconazole", "18");
		pgkbDrugCount.put("vorinostat", "10");
		pgkbDrugCount.put("warfarin", "96");
		pgkbDrugCount.put("Watersoluble", "3");
		pgkbDrugCount.put("X-rayContrastMedia", "2");
		pgkbDrugCount.put("xanomeline", "2");
		pgkbDrugCount.put("xanthophyll", "5");
		pgkbDrugCount.put("xenobiotics", "12");
		pgkbDrugCount.put("ximelagatran", "7");
		pgkbDrugCount.put("XR9051", "8");
		pgkbDrugCount.put("yohimbine", "27");
		pgkbDrugCount.put("zafirlukast", "11");
		pgkbDrugCount.put("zalcitabine", "9");
		pgkbDrugCount.put("zaleplon", "16");
		pgkbDrugCount.put("zanamivir", "13");
		pgkbDrugCount.put("zidovudine", "63");
		pgkbDrugCount.put("zileuton", "31");
		pgkbDrugCount.put("zincacetate", "1");
		pgkbDrugCount.put("zincoxide", "1");
		pgkbDrugCount.put("zincsulfate", "2");
		pgkbDrugCount.put("ziprasidone", "34");
		pgkbDrugCount.put("zoledronate", "41");
		pgkbDrugCount.put("zolmitriptan", "5");
		pgkbDrugCount.put("zolpidem", "6");
		pgkbDrugCount.put("zonisamide", "10");
		pgkbDrugCount.put("zopiclone", "12");
		pgkbDrugCount.put("Zosuquidar", "8");
		pgkbDrugCount.put("zuclopenthixol", "14");

	} // END OF PGKB DRUG Count

	// Getters

	public HashMap<String, String> getpgkbPathwayCount() {
		return (HashMap<String, String>) pgkbPathwayCount;
	}

	public HashMap<String, String> getpgkbDiseaseCount() {
		return (HashMap<String, String>) pgkbDiseaseCount;
	}

	public HashMap<String, String> getpgkbDrugCount() {
		return (HashMap<String, String>) pgkbDrugCount;
	}

} // END of BioEnrichment2
