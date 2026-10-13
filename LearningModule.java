/*Contribute by: Lim Gek Hong (104763)
    Tested by Loh Zhi Yun (104788)
              Jee Hui Ying (104511)
    Debugged by Jee Hui Ying (104511)*/

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;



public class LearningModule extends JPanel implements LearningModuleBehavior
{

    // attributes (data/logic)
    private List<String> pages;
    // keep track of current page number
    private int currentPage;

    // attributes (UI)
    private JEditorPane contentPane;
    private JLabel pageLabel;
    private JButton quitBtn;
    private JButton nextBtn;
    private JButton prevBtn;
    private MainPage mainPage;


    public LearningModule(MainPage mainPage) {
        this.mainPage = mainPage;  //the program is saving the MainPage object so it can be used later in this class.
        this.pages = new ArrayList<>();
        this.currentPage = 0;

        // load image paths from images folder
        String climateImgPath = resolveImagePath("Climate.png");
        String climateChangeImgPath = resolveImagePath("ClimateChange.jpg");
        String generatingPowerImgPath = resolveImagePath("GeneratingPower.png");
        String manufacturingImgPath = resolveImagePath("manufacturing.png");
        String cutForestImgPath = resolveImagePath("CutForest.png");
        String transportImgPath = resolveImagePath("Transportation.png");
        String produceFoodImgPath = resolveImagePath("ProduceFood.png");
        String powerBuildingImgPath = resolveImagePath("PowerBuilding.png");
        String overconsumptionImgPath = resolveImagePath("overconsumption.png");
        String hotWeatherImgPath=resolveImagePath("HotWeather.png");
        String stormImgPath=resolveImagePath("Storm.png");
        String droughtImgPath=resolveImagePath("Drought.png");
        String warmingImgPath=resolveImagePath("WaterRising.png");
        String lossSpeciesImgPath=resolveImagePath("LossSpecies.png");
        String noFoodImgPath=resolveImagePath("NoFood.png");
        String healthRiskImgPath=resolveImagePath("HealthRisk.png");
        String povertyImgPath=resolveImagePath("Poverty.png");
        String sdgTargetImgPath=resolveImagePath("SDGTarget.png");
        String climateActionImgPath=resolveImagePath("ClimateAction.png");
        String cognitiveImgPath=resolveImagePath("CognitiveLearning.png");
        String socioImgPath=resolveImagePath("SocioEmotional.png");
        String behaviourImgPath=resolveImagePath("BehavioralLearning.png");
        String renewableImgPath=resolveImagePath("RenewableEnergy.png");
        String carbonStorageImgPath=resolveImagePath("CarbonStorage.png");
        String policyCoherenceImgPath=resolveImagePath("PolicyCoherence.png");
        String insufficientFinanceImgPath=resolveImagePath("InsufficientFinance.png");
        String poorCoordinationImgPath=resolveImagePath("PoorCoordination.png");
        String individuActionImgPath=resolveImagePath("IndividuAction.png");
        String communityEngagementImgPath=resolveImagePath("Community.png");
        String callToActionImgPath=resolveImagePath("CallToAction.png");
        String greenFinanceImgPath=resolveImagePath("GreenFinance.png");
        String governmentSupportImgPath=resolveImagePath("GovernmentSupport.png");

        // Load all learning module pages with text and images
        loadPages(climateImgPath, climateChangeImgPath,generatingPowerImgPath,manufacturingImgPath,cutForestImgPath,
                transportImgPath,produceFoodImgPath, powerBuildingImgPath,overconsumptionImgPath,hotWeatherImgPath
                ,stormImgPath,droughtImgPath,warmingImgPath,lossSpeciesImgPath,noFoodImgPath,healthRiskImgPath,povertyImgPath
                , sdgTargetImgPath,climateActionImgPath,cognitiveImgPath,socioImgPath,behaviourImgPath,renewableImgPath
                ,carbonStorageImgPath,policyCoherenceImgPath,insufficientFinanceImgPath,poorCoordinationImgPath
                ,individuActionImgPath,communityEngagementImgPath,callToActionImgPath,greenFinanceImgPath,governmentSupportImgPath);

        buildUI();//  create and arrange GUI components
        displayPage(0);// display the first page when module starts

    }

    //  LearningModuleBehavior methods (interface implementation)

    @Override
    public void nextPage() {
        // move to next page if current page is not the last page
        if (currentPage < pages.size() - 1) {
            currentPage++;
        }
    }

    @Override
    public void previousPage() {
        // move to previous page if current page is not the first page
        if (currentPage > 0) {
            currentPage--;
        }
    }

    @Override
    public void displayPage(int page) {
        // update current page if page number is valid
        if (page >= 0 && page < pages.size()) {
            currentPage = page;
        }

        // display page content
        contentPane.setText(pages.get(currentPage));

        //scroll back to top when a new page is shown
        SwingUtilities.invokeLater(() -> contentPane.setCaretPosition(0));

        // update page number label
        pageLabel.setText("Page " + (currentPage + 1) + " / " + pages.size());

        //disable previous button on first page
        prevBtn.setEnabled(currentPage > 0);

        //disable next button on last page
        nextBtn.setEnabled(currentPage < pages.size()-1);
    }

    @Override
    public String getCurrentContent() {
        // return content of current page
        return pages.get(currentPage);
    }

    @Override
    public int getCurrentPageNumber() {
        // return current page index
        return currentPage;
    }

    @Override
    public int getTotalPages() {
        // return total number of pages
        return pages.size();
    }


    // UI methods

    private String resolveImagePath(String filename) {
        // try to load image from resources folder（images folder)
        URL url = getClass().getResource("/images/" + filename);
        if (url != null) return url.toExternalForm();

        // try alternative image locations
        for (String p : new String[]{"src/images/" + filename, "images/" + filename}) {
            java.io.File f = new java.io.File(p);
            if (f.exists()) {
                try { return f.toURI().toURL().toExternalForm(); }
                catch (Exception e) { return ""; }
            }
        }

        // return empty string if image is not found
        return "";
    }

    private void buildUI() {


        // set the main panel layout and background colour
        setLayout(new BorderLayout());
        setBackground(new Color(220, 245, 220));


         // create content area for displaying HTML pages
        contentPane = new JEditorPane() {
            @Override
            public boolean getScrollableTracksViewportWidth() {
                return true;
            }
        };
        contentPane.setContentType("text/html");
        contentPane.setEditable(false);
        contentPane.setOpaque(false);
        contentPane.setBorder(null);
        contentPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);

        // open hyperlink in default browser when clicked(like pdf links,reference links ,youtube links)
        contentPane.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().browse(new URI(e.getURL().toString()));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        // add scroll pane for page content so can scroll down and up
        JScrollPane scroll = new JScrollPane(contentPane);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getViewport().setBackground(new Color(220, 245, 220));
        add(scroll, BorderLayout.CENTER);


        // create bottom navigation panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(34, 139, 34));
        bottomPanel.setPreferredSize(new Dimension(450, 80));

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(34, 139, 34));

        // create buttons
        quitBtn = new JButton("Quit");
        prevBtn = new JButton("Previous");
        nextBtn = new JButton("Next");

        // apply button styling
        styleButton(quitBtn);
        styleButton(prevBtn);
        styleButton(nextBtn);
        btnPanel.add(quitBtn);
        btnPanel.add(prevBtn);
        btnPanel.add(nextBtn);

        pageLabel = new JLabel("", SwingConstants.CENTER);
        pageLabel.setForeground(Color.WHITE);
        pageLabel.setFont(new Font("Arial", Font.BOLD, 14));

        bottomPanel.add(btnPanel, BorderLayout.NORTH);
        bottomPanel.add(pageLabel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);


        // return to main page when quit button is clicked
        quitBtn.addActionListener(e -> {
            mainPage.dispose();
            new MainPage();
        
        });

        // display previous page
        prevBtn.addActionListener(e -> {
            previousPage();
            displayPage(currentPage);
        });

        // display next page
        nextBtn.addActionListener(e -> {

                nextPage();
                displayPage(currentPage);
            }
        );
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(34, 139, 34));
        button.setFocusPainted(false);
    }

    // Page content

    private void loadPages(String img1, String img2,String img3,String img4,String img5,String img6,String img7,String img8,String img9,String img10,String img11,String img12,String img13,String img14,String img15,String img16,String img17,String img18,String img19,String img20,String img21,String img22,String img23,String img24,String img25,String img26,String img27,String img28,String img29,String img30,String img31,String img32) {

        //page 1
        pages.add("""
<html>
<body style="
    background-color:transparent;
    margin:10px;
    font-family:Arial;
    line-height:1.6;
    max-width:100%;
    overflow-x:hidden;
    color:#222;
">

<h2 style="
    background-color:#2E7D32;
    color:white;
    padding:12px;
    border-radius:10px;
    font-size:18px;
">
Page 1: Introduction to SDG 13 – What Is Climate Action?
</h2>


<div style="
    text-align:center;
    margin-top:10px;
    padding:10px;
    background-color:#E8F5E9;
    border:2px solid #2E7D32;
    border-radius:10px;
">

   <img src='##CLIMATE_IMG##'
        width="320">

</div>

<div style="
    background-color:#F1F8E9;
    padding:12px;
    border-radius:10px;
    margin-top:10px;
">
<b>Please go through the videos:</b><br><br>

<a href="https://youtu.be/TPGH-i29myA?si=iBqmnjS2EVSeTrgW"
style="word-break:break-word;">
https://youtu.be/TPGH-i29myA?si=iBqmnjS2EVSeTrgW
</a>

<br><br>

<a href="https://youtu.be/jhoa3OHivN8?si=czuTjbUv-5X0pWzr"
style="word-break:break-word;">
https://youtu.be/jhoa3OHivN8?si=czuTjbUv-5X0pWzr
</a>
</div>

<br>

<div style="
    background-color:#E8F5E9;
    padding:12px;
    border-radius:10px;
">
<b>Definition of SDG 13:</b><br><br>

“Take urgent action to combat climate change and its impacts”
</div>

<br>

<div style="
    background-color:#F5F5F5;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Quick Intro: SDG 13 – Climate Action and Climate Change</h3>

<p>
SDG 13 is the UN’s goal on “Climate Action”. Its core aim is to take urgent action to combat climate change and its impacts as part of the 2030 Agenda for Sustainable Development.
</p>

</div>

<br>

<div style="
    background-color:#FFF8E1;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">How SDG 13 Relates to Climate Change</h3>

<p>
SDG 13 was created because climate change is now seen as one of the most serious threats to people and the planet, affecting health, food, water, economies, and ecosystems.
</p>

<p>
It recognises climate change as a global challenge that requires coordinated action by governments, businesses, communities, and individuals.
</p>

</div>

<br>

<div style="
    background-color:#E3F2FD;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Key SDG 13 Priorities Include:</h3>

<ul style="padding-left:20px;">

<li>Reducing greenhouse gas emissions (mitigation)</li>

<li>Adapting to climate impacts and building resilience to climate-related disasters</li>

<li>Integrating climate measures into national policies and planning</li>

<li>Improving education and awareness about climate change (Target 13.3)</li>

<li>Strengthening institutions and capacity, especially in vulnerable and least-developed countries</li>

</ul>

</div>

<br>

<div style="
    background-color:#FCE4EC;
    padding:12px;
    border-radius:10px;
">

<p style="margin:0;">
Because climate change affects almost every part of life, SDG 13 is described as a central, cross-cutting goal that is closely linked with many other SDGs such as energy, food, poverty, health, and ecosystems.
</p>

</div>

</body>
</html>
""".replace("##CLIMATE_IMG##", img1));
        //page 2
        pages.add("""
<html>
<body style="
    background-color:transparent;
    margin:10px;
    font-family:Arial;
    line-height:1.6;
    max-width:100%;
    overflow-x:hidden;
    color:#222;
">

<h2 style="
    background-color:#2E7D32;
    color:white;
    padding:12px;
    border-radius:10px;
    font-size:18px;
">
Page 2: The Causes of Climate Change
</h2>

<div style="
    background-color:#F1F8E9;
    padding:12px;
    border-radius:10px;
    margin-top:10px;
">
<b>Learning Website:</b><br><br>

<a href="https://www.un.org/en/climatechange/science/causes-effects-climate-change"
style="word-break:break-word;">
https://www.un.org/en/climatechange/science/causes-effects-climate-change
</a>
</div>

<br>

<div style="
    background-color:#E8F5E9;
    padding:12px;
    border-radius:10px;
">



<h3 style="margin-top:0;">What Is Climate Change?</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##CLIMATECHANGE_IMG##' width="320">
</div>

<p>
Climate change refers to long-term changes in temperature and weather patterns. These changes can occur naturally, for example through variations in solar activity or major volcanic eruptions.
</p>

<p>
However, since the 1800s, human activities have become the primary cause of climate change, mainly due to the burning of fossil fuels such as coal, oil, and natural gas.
</p>

</div>

<br>

<div style="
    background-color:#FFF8E1;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Main Causes of Climate Change</h3>

<p>
Climate change is mainly caused by human activities that release greenhouse gases like carbon dioxide (CO₂) and methane into the atmosphere. These gases trap heat and make the Earth warmer.
</p>

</div>

<br>

<div style="
    background-color:#E3F2FD;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">1. Generating Power</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##GENERATINGPOWER_IMG##' width="320">
</div>

<p>
Most electricity is produced by burning fossil fuels like coal, oil, and gas.
</p>

<ul style="padding-left:20px;">
<li>Produces large amounts of CO₂</li>
<li>Used for electricity and heating</li>
<li>Renewable energy (solar and wind) produces much less pollution</li>
</ul>

<p>
This is one of the biggest sources of global emissions.
</p>

</div>

<br>

<div style="
    background-color:#F5F5F5;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">2. Manufacturing Goods</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##MANUFACTURING_IMG##' width="320">
</div>

<p>
Factories produce emissions when making products such as:
</p>

<ul style="padding-left:20px;">
<li>Steel and cement</li>
<li>Electronics and plastics</li>
<li>Clothes and machines</li>
<li>Uses fossil fuels for energy</li>
<li>Industrial processes also release greenhouse gases</li>
</ul>

<p>
Industry is one of the largest contributors to climate change.
</p>

</div>

<br>

<div style="
    background-color:#FFF3E0;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">3. Cutting Down Forests</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##CUTFOREST_IMG##' width="320">
</div>

<p>
Trees store carbon, but when they are cut down:
</p>

<ul style="padding-left:20px;">
<li>Stored carbon is released into the air</li>
<li>Fewer trees remain to absorb CO₂</li>
<li>Often done for farming or land use</li>
<li>Causes about one-third of global emissions when combined with agriculture</li>
</ul>

</div>

<br>

<div style="
    background-color:#E1F5FE;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">4. Using Transportation</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##TRANSPORT_IMG##' width="320">
</div>


<p>
Cars, buses, ships, and planes mostly use fossil fuels.
</p>

<ul style="padding-left:20px;">
<li>Releases large amounts of CO₂</li>
<li>Road transport is the biggest contributor</li>
<li>Air and sea transport are increasing emissions</li>
</ul>

<p>
Transport is responsible for about one-quarter of global energy emissions.
</p>

</div>

<br>

<div style="
    background-color:#F1F8E9;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">5. Producing Food</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##PRODUCEFOOD_IMG##' width="320">
</div>

<p>
Food production creates emissions through:
</p>

<ul style="padding-left:20px;">
<li>Farming livestock (methane from cows)</li>
<li>Fertilizer use</li>
<li>Land clearing and deforestation</li>
<li>Transport and packaging</li>
</ul>

<p>
Agriculture is a major climate contributor.
</p>

</div>

<br>

<div style="
    background-color:#FCE4EC;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">6. Powering Buildings</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##POWERBUILDING_IMG##' width="320">
</div>

<p>
Buildings use a lot of energy for:
</p>

<ul style="padding-left:20px;">
<li>Heating</li>
<li>Cooling (air conditioning)</li>
<li>Lighting and appliances</li>
<li>Often powered by fossil fuels</li>
<li>Energy demand is increasing worldwide</li>
</ul>

</div>

<br>

<div style="
    background-color:#ECEFF1;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">7. Overconsumption</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##OVERCONSUMPTION_IMG##' width="320">
</div>


<p>
Human lifestyle choices also affect the climate:
</p>

<ul style="padding-left:20px;">
<li>Using too much electricity and fuel</li>
<li>Buying excess clothing and electronics</li>
<li>Producing too much waste</li>
</ul>

<p>
Wealthier countries and people contribute the most emissions globally.
</p>

</div>

<br>

<div style="
    background-color:#FAFAFA;
    padding:12px;
    border-radius:10px;
   
">

<b>Reference:</b><br><br>

United Nations. (n.d.-b). <i>What is climate change?</i> | United Nations.<br><br>

<a href="https://www.un.org/en/climatechange/what-is-climate-change"
style="word-break:break-word;">
https://www.un.org/en/climatechange/what-is-climate-change
</a>

</div>

</body>
</html>
""".replace("##CLIMATECHANGE_IMG##", img2) .replace("##GENERATINGPOWER_IMG##", img3).replace("##MANUFACTURING_IMG##", img4)
                .replace("##CUTFOREST_IMG##", img5).replace("##TRANSPORT_IMG##", img6).replace("##PRODUCEFOOD_IMG##", img7)
                .replace("##POWERBUILDING_IMG##", img8).replace("##OVERCONSUMPTION_IMG##", img9));

        //page 3
        pages.add("""
<html>
<body style="
    background-color:transparent;
    margin:10px;
    font-family:Arial;
    line-height:1.6;
    max-width:100%;
    overflow-x:hidden;
    color:#222;
">

<h2 style="
    background-color:#2E7D32;
    color:white;
    padding:12px;
    border-radius:10px;
    font-size:18px;
">
Page 3: The Impact of Climate Change
</h2>

<div style="
    background-color:#F1F8E9;
    padding:12px;
    border-radius:10px;
    margin-top:10px;
">
<b>Learning Website:</b><br><br>

<a href="https://www.un.org/en/climatechange/science/causes-effects-climate-change"
style="word-break:break-word;">
https://www.un.org/en/climatechange/science/causes-effects-climate-change
</a>
</div>

<br>

<div style="
    background-color:#E8F5E9;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Impacts of Climate Change</h3>

<p>
Climate change is already affecting the planet in many serious ways. As greenhouse gases continue to trap heat in the atmosphere, global temperatures rise and cause widespread environmental, social, and economic impacts.
</p>

</div>

<br>

<div style="
    background-color:#FFF3E0;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Hotter Temperatures</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##HOTWEATHER_IMG##' width="320">
</div>


<p>
As greenhouse gas levels increase, global temperatures also rise. The last decade (2015–2024) has been the hottest on record, and each decade since the 1980s has been warmer than the one before.
</p>

<p>
Nearly all regions are now experiencing more frequent heatwaves and hotter days. These extreme temperatures increase health risks such as heatstroke and make it harder for people to work outdoors.
</p>

<p>
Hot conditions also make wildfires more likely and more intense, especially in dry regions. The Arctic is warming even faster, at least twice the global average.
</p>

</div>

<br>

<div style="
    background-color:#E3F2FD;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">More Severe Storms</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##STORMS_IMG##' width="320">
</div>


<p>
Climate change is making storms stronger and more destructive. Higher temperatures cause more water to evaporate, which increases moisture in the atmosphere.
</p>

<p>
This leads to heavier rainfall and more severe flooding. Warmer ocean temperatures also fuel tropical storms such as hurricanes, cyclones, and typhoons.
</p>

<p>
These storms can destroy homes, damage infrastructure, and lead to loss of life and large economic losses.
</p>

</div>

<br>

<div style="
    background-color:#FFF8E1;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Increased Drought</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##DROUGHT_IMG##' width="320">
</div>


<p>
Many regions are experiencing reduced water availability due to climate change. Rising temperatures worsen water shortages, especially in already dry areas.
</p>

<p>
This leads to agricultural droughts that affect crop production and ecological droughts that damage ecosystems.
</p>

<p>
In some cases, droughts also trigger dust storms and desert expansion, reducing the amount of land available for farming. As a result, many communities face increasing water insecurity.
</p>

</div>

<br>

<div style="
    background-color:#E1F5FE;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Warming and Rising Oceans</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##WARMING_IMG##' width="320">
</div>


<p>
The ocean absorbs most of the excess heat caused by global warming. As a result, ocean temperatures are increasing at all depths.
</p>

<p>
Warmer water expands, and melting ice sheets add more water to the sea, causing sea levels to rise. This threatens coastal cities and small island nations.
</p>

<p>
In addition, the ocean absorbs carbon dioxide, which makes it more acidic and harms marine life such as coral reefs and fish ecosystems.
</p>

</div>

<br>

<div style="
    background-color:#FCE4EC;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Loss of Species</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##LOSSSPECIES_IMG##' width="320">
</div>


<p>
Climate change is threatening biodiversity around the world. Many species are struggling to survive as their habitats change too quickly.
</p>

<p>
Extreme weather events, wildfires, rising temperatures, and disease spread are all contributing to this loss.
</p>

<p>
Scientists estimate that extinction rates are now far higher than natural levels, and many species may disappear in the coming decades.
</p>

<p>
While some animals may adapt or migrate, others cannot survive the rapid changes.
</p>

</div>

<br>

<div style="
    background-color:#F1F8E9;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Not Enough Food</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##NOFOOD_IMG##' width="320">
</div>


<p>
Climate change is affecting global food production. Extreme weather, droughts, and floods can destroy crops and reduce agricultural productivity.
</p>

<p>
Fisheries are also affected as ocean warming and acidification harm marine life.
</p>

<p>
Livestock farming is impacted by heat stress and reduced water and grass availability. These changes threaten global food security and increase the risk of hunger and malnutrition.
</p>

</div>

<br>

<div style="
    background-color:#F3E5F5;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">More Health Risks</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##HEALTHRISK_IMG##' width="320">
</div>


<p>
Climate change is a serious threat to human health. It contributes to air pollution, the spread of diseases, extreme weather injuries, mental health stress, and food shortages.
</p>

<p>
Millions of people are affected each year, especially in vulnerable regions.
</p>

<p>
Changes in climate patterns also allow some diseases to spread to new areas, putting more pressure on healthcare systems.
</p>

</div>

<br>

<div style="
    background-color:#ECEFF1;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Poverty and Displacement</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##POVERTY_IMG##' width="320">
</div>


<p>
Climate change increases poverty and forces people to leave their homes.
</p>

<p>
Floods, droughts, and storms can destroy houses, farmland, and jobs. Heat stress also reduces the ability to work, especially in outdoor industries.
</p>

<p>
In recent years, millions of people have been displaced due to climate-related disasters, with vulnerable communities being the most affected.
</p>

</div>

</body>
</html>
""".replace("##HOTWEATHER_IMG##", img10) .replace("##STORMS_IMG##", img11).replace("##DROUGHT_IMG##", img12)
                .replace("##WARMING_IMG##", img13).replace("##LOSSSPECIES_IMG##", img14).replace("##NOFOOD_IMG##", img15)
                .replace("##HEALTHRISK_IMG##", img16).replace("##POVERTY_IMG##", img17));

        //page 4
        pages.add("""
<html>
<body style="
    background-color:transparent;
    margin:10px;
    font-family:Arial;
    line-height:1.6;
    max-width:100%;
    overflow-x:hidden;
    color:#222;
">

<h2 style="
    background-color:#2E7D32;
    color:white;
    padding:12px;
    border-radius:10px;
    font-size:18px;
">
Page 4: SDG 13 Targets and Indicators
</h2>

<div style="
    background-color:#F1F8E9;
    padding:12px;
    border-radius:10px;
    margin-top:10px;
">

<b>Learning Website:</b><br><br>

<a href="https://sdgs.un.org/goals/goal13#targets_and_indicators"
style="word-break:break-word;">
https://sdgs.un.org/goals/goal13#targets_and_indicators
</a>

</div>

<br>

<div style="
    background-color:#E8F5E9;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Detailed Breakdown of SDG 13’s Official Targets</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##SDGTARGET_IMG##' width="320">
</div>


<ul style="padding-left:20px;">
<li><b>13.1:</b> Strengthen resilience and adaptive capacity to climate-related hazards.</li>

<li><b>13.2:</b> Integrate climate measures into national policies and planning.</li>

<li><b>13.3:</b> Improve education, awareness, and capacity on mitigation and adaptation.</li>

<li><b>13.a / 13.b:</b> Mobilize finance and support for developing countries.</li>
</ul>

</div>

<br>

<div style="
    background-color:#E3F2FD;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">How Progress Is Measured</h3>

<p>
SDG 13 progress is measured using indicators such as:
</p>

<ul style="padding-left:20px;">
<li>Disaster risk reduction strategies</li>
<li>Greenhouse gas (GHG) emissions per year</li>
<li>Climate adaptation planning</li>
<li>Education and awareness programs</li>
<li>National climate policy implementation</li>
</ul>

<p>
These indicators help track how effectively countries are responding to climate change and building resilience for the future.
</p>

</div>

</body>
</html>
""".replace("##SDGTARGET_IMG##", img18));

//page 5 content
        pages.add("""
<html>
<body style="
    background-color:transparent;
    margin:10px;
    font-family:Arial;
    line-height:1.6;
    max-width:100%;
    overflow-x:hidden;
    color:#222;
">

<h2 style="
    background-color:#2E7D32;
    color:white;
    padding:12px;
    border-radius:10px;
    font-size:18px;
">
Page 5: Why Climate Action Matters
</h2>

<div style="
    background-color:#FFF3E0;
    padding:12px;
    border-radius:10px;
">

<p>
Climate action is important because climate change is causing rising temperatures, floods, droughts, wildfires, hurricanes, and sea level rise across the world.
</p>

<p>
These environmental changes threaten ecosystems, economies, food supplies, and human health.
</p>

<p>
The years 2010–2019 were recorded as the warmest decade in history, showing that urgent action is needed.
</p>

</div>

<br>

<div style="
    background-color:#FCE4EC;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">What Happens If Climate Change Continues?</h3>

<p>
If climate change continues unchecked, global temperatures could rise above 3°C, leading to:
</p>

<ul style="padding-left:20px;">
<li>Stronger natural disasters</li>
<li>Water shortages</li>
<li>Crop failures</li>
<li>Biodiversity loss</li>
<li>Increased poverty</li>
</ul>

<p>
Vulnerable communities and developing countries are especially at risk.
</p>

</div>

<br>

<div style="
    background-color:#E8F5E9;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Benefits of Climate Action</h3>

<p>
Taking climate action brings many important benefits.
</p>

<ul style="padding-left:20px;">
<li>Improves air quality</li>
<li>Protects public health</li>
<li>Prevents millions of premature deaths</li>
<li>Reduces diseases caused by polluted air</li>
<li>Improves food security</li>
<li>Protects agriculture from extreme weather</li>
</ul>

</div>

<br>

<div style="
    background-color:#E3F2FD;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Economic and Sustainable Benefits</h3>

<p>
Investing in renewable energy and cleaner technologies can:
</p>

<ul style="padding-left:20px;">
<li>Create green jobs</li>
<li>Increase economic growth</li>
<li>Reduce poverty</li>
<li>Build more sustainable communities</li>
</ul>

<p>
Experts also state that climate solutions are practical, cost-effective, and can help slow global warming by up to 0.6°C by 2050.
</p>

</div>

<br>

<div style="
    background-color:#F1F8E9;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Global Cooperation</h3>

<p>
Global cooperation, such as the Paris Agreement, aims to limit global warming to 1.5°C by reducing greenhouse gas emissions and promoting sustainable development for future generations.
</p>



</div>

<br>



<div style="
    background-color:#FAFAFA;
    padding:12px;
    border-radius:10px;
 
">


<b>Read:</b><br><br>

<a href="https://www.ccacoalition.org/content/why-we-need-act-now"
style="word-break:break-word;">
https://www.ccacoalition.org/content/why-we-need-act-now
</a>

<br><br>

<a href="https://www.un.org/sustainabledevelopment/wp-content/uploads/2019/07/13_Why-It-Matters-2020.pdf"
style="word-break:break-word;">
https://www.un.org/sustainabledevelopment/wp-content/uploads/2019/07/13_Why-It-Matters-2020.pdf
</a>

</div>

<div style="
    text-align:center;
    margin-top:10px;
    margin-bottom:15px;
    padding:10px;
    background-color:#E8F5E9;
    border:2px solid #2E7D32;
    border-radius:10px;
">

   <img src='##CLIMATEACTION_IMG##'
        width="320"
        style="border-radius:8px;">

</div>

</body>
</html>
""".replace("##CLIMATEACTION_IMG##", img19));

        //page 6 content
        pages.add("""
<html>
<body style="
    background-color:transparent;
    margin:10px;
    font-family:Arial;
    line-height:1.6;
    max-width:100%;
    overflow-x:hidden;
    color:#222;
">

<h2 style="
    background-color:#2E7D32;
    color:white;
    padding:12px;
    border-radius:10px;
    font-size:18px;
">
Page 6: Synergies & Trade-offs: SDG 13 and Other Goals
</h2>


<div style="
    background-color:#F1F8E9;
    padding:12px;
    border-radius:10px;
    margin-top:10px;
    text-align:justify;
">

<p style="margin:0;">
<b>Read (PDF):</b>
</p>

<a href="https://www.researchgate.net/publication/347864281_Synergies_and_Trade-offs_among_Sustainable_Development_Goals_The_Case_of_Spain">
Click here to access the PDF article
</a>

</div>

<br>

<div style="
    background-color:#E8F5E9;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Positive Synergies</h3>

<p>
SDG 13 works closely with many other Sustainable Development Goals.
</p>

<ul style="padding-left:20px;">
<li>Clean Energy (SDG 7)</li>
<li>Sustainable Cities and Communities (SDG 11)</li>
<li>Responsible Consumption and Production (SDG 12)</li>
<li>Life Below Water (SDG 14)</li>
<li>Life on Land (SDG 15)</li>
</ul>

<p>
Climate action can support cleaner energy systems, sustainable cities, environmental protection, and responsible resource use.
</p>

</div>

<br>

<div style="
    background-color:#FFF3E0;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Potential Trade-offs</h3>

<p>
There can also be trade-offs between SDG 13 and other development goals.
</p>

<ul style="padding-left:20px;">
<li>Economic growth may increase emissions</li>
<li>Agricultural expansion can contribute to deforestation</li>
<li>Industrial development may increase energy use</li>
</ul>

<p>
These challenges show the importance of careful policy design and sustainable planning.
</p>

</div>

<br>

<div style="
    background-color:#E3F2FD;
    padding:12px;
    border-radius:10px;
">

<h3 style="margin-top:0;">Integrated Approaches</h3>

<p>
Integrated approaches are important to maximize co-benefits and minimize conflicts among SDGs.
</p>

<p>
Countries and organizations should develop policies that balance environmental, social, and economic goals together.
</p>

</div>

</body>
</html>
""");

//page 7
        pages.add("""
<html>
<body style="
    background-color:transparent;
    margin:10px;
    font-family:Arial;
    line-height:1.6;
    max-width:100%;
    overflow-x:hidden;
    color:#222;
">

<h2 style="
    background-color:#2E7D32;
    color:white;
    padding:12px;
    border-radius:10px;
    font-size:18px;
">
Page 7: Education & Awareness for Climate Action
</h2>

<br>

<div style="background-color:white; padding:14px; border-radius:12px;">

<h3 style="color:#2e7d32;">Why Climate Education Matters</h3>

<p>
Education for climate action is not only about learning facts. It helps people build:
</p>

<ul>
    <li>understanding of climate change,</li>
    <li>emotional connection to environmental issues,</li>
    <li>and real-life actions to protect the planet.</li>
</ul>

<p>
According to UNESCO, effective SDG 13 education combines:
</p>

<ul>
    <li>Cognitive learning</li>
    <li>Socio-emotional learning</li>
    <li>Behavioural learning</li>
</ul>

<p>
Together, these help students become informed and responsible global citizens.
</p>

</div>

<br>

<div style="background-color:#e8f5e9; padding:14px; border-radius:12px;">

<h3 style="color:#1b5e20;">Cognitive Learning</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##COGNITIVELEARNING_IMG##' width="320">
</div>


<h4>Understanding Causes and Consequences</h4>

<p>
Cognitive learning focuses on climate knowledge and understanding.
</p>

<p>Students learn:</p>

<ul>
    <li>how climate change happens,</li>
    <li>causes such as greenhouse gases and pollution,</li>
    <li>environmental and social impacts,</li>
    <li>and possible mitigation and adaptation solutions.</li>
</ul>

<p>This also includes:</p>

<ul>
    <li>System knowledge → how the climate system works</li>
    <li>Action knowledge → what actions can reduce climate change</li>
    <li>Effectiveness knowledge → which actions are most effective</li>
</ul>

<p>
However, many students only understand basic facts and struggle to understand:
</p>

<ul>
    <li>complex climate systems,</li>
    <li>long-term impacts,</li>
    <li>and connections between human activities and climate change.</li>
</ul>

</div>

<br>

<div style="background-color:#f1f8e9; padding:14px; border-radius:12px;">

<h3 style="color:#33691e;">Socio-Emotional Learning</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##SOCIALLEARNING_IMG##' width="320">
</div>


<h4>Values, Concern, and Connection</h4>

<p>
Socio-emotional learning develops:
</p>

<ul>
    <li>empathy,</li>
    <li>responsibility,</li>
    <li>environmental values,</li>
    <li>and emotional connection to nature and communities.</li>
</ul>

<p>
Education that encourages:
</p>

<ul>
    <li>hope,</li>
    <li>care for the environment,</li>
    <li>and place-based experiences</li>
</ul>

<p>
can strengthen students’ motivation and confidence to take climate action.
</p>

<p>Examples include:</p>

<ul>
    <li>community activities,</li>
    <li>outdoor learning,</li>
    <li>and local environmental projects.</li>
</ul>

<p>
These experiences help students feel that their actions matter.
</p>

</div>

<br>

<div style="background-color:#e3f2fd; padding:14px; border-radius:12px;">

<h3 style="color:#1565c0;">Behavioural Learning</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##BEHAVIORLEARNING_IMG##' width="320">
</div>


<h4>Skills and Real Action</h4>

<p>
Behavioural learning focuses on turning knowledge into action.
</p>

<p>Students learn practical skills such as:</p>

<ul>
    <li>calculating carbon footprints,</li>
    <li>reducing waste,</li>
    <li>saving electricity,</li>
    <li>and participating in community projects.</li>
</ul>

<p>
UNESCO emphasizes behavioural objectives because real climate action requires behaviour change.
</p>

<p>
However, studies show that:
</p>

<ul>
    <li>many programs improve knowledge,</li>
    <li>but actual behaviour often changes very little.</li>
</ul>

<p><b>“Knowledge–Action Gap”</b></p>

</div>

<br>

<div style="background-color:#fff8e1; padding:14px; border-radius:12px;">

<h3 style="color:#ef6c00;">Challenges in Turning Knowledge into Action</h3>

<p>
Even when students understand climate change, many still face barriers to action.
</p>

<h4>Common Challenges</h4>

<ul>
    <li>Misconceptions about climate change causes and impacts</li>
    <li>Limited understanding of effective mitigation actions</li>
    <li>Focus only on small personal actions instead of larger systemic change</li>
    <li>Climate anxiety and feelings of helplessness</li>
    <li>Lack of confidence that individual actions make a difference</li>
    <li>Socioeconomic inequalities that limit access to climate education</li>
</ul>

<p>
Many climate education programs focus too heavily on science facts while giving less attention to:
</p>

<ul>
    <li>emotions,</li>
    <li>justice,</li>
    <li>and practical action.</li>
</ul>

<p>
As a result, awareness increases, but behaviour may not change significantly.
</p>

</div>

<br>

<div style="background-color:#ede7f6; padding:14px; border-radius:12px;">

<h3 style="color:#5e35b1;">Promising Approaches for SDG 13 Education</h3>

<p>
Research shows that climate education becomes more effective when learning is:
</p>

<ul>
    <li>participatory,</li>
    <li>experiential,</li>
    <li>place-based,</li>
    <li>and gamified.</li>
</ul>

<p>Programs that teach:</p>

<ul>
    <li>practical climate actions,</li>
    <li>self-efficacy,</li>
    <li>and realistic solutions</li>
</ul>

<p>
can improve both motivation and real behaviour change.
</p>

<p>Encouraging:</p>

<ul>
    <li>hope,</li>
    <li>teamwork,</li>
    <li>and achievable actions</li>
</ul>

<p>
helps students move from awareness to long-term climate engagement.
</p>

</div>

<br>

<div style="background-color:white; padding:14px; border-radius:12px;">

<h3 style="color:#2e7d32;">References</h3>

<p>
Gonzalez, F. H. (2023). <i>Exploring the Affordances of Place-Based Education for Advancing Sustainability Education: The Role of Cognitive, Socio-Emotional and Behavioural Learning</i><br>
<a href="https://doi.org/10.3390/educsci13080832" style="word-break:break-word;">
https://doi.org/10.3390/educsci13080832
</a>
</p>

<p>
Algurén, B. (2021). <i>How to Bring About Change – A Literature Review About Education and Learning Activities for Sustainable Development</i><br>
<a href="https://doi.org/10.2478/dcse-2021-0010" style="word-break:break-word;">
https://doi.org/10.2478/dcse-2021-0010
</a>
</p>

<p>
Bottin, M., et al. (2026). <i>Systematic Review of the Effects of Climate Change Education on Cognition, Attitude, and Behavior of Schoolchildren and Their Entourage</i><br>
<a href="https://doi.org/10.1088/1748-9326/ad40d2" style="word-break:break-word;">
https://doi.org/10.1088/1748-9326/ad40d2
</a>
</p>

</div>

</body>
</html>
""".replace("##COGNITIVELEARNING_IMG##", img20) .replace("##SOCIALLEARNING_IMG##", img21).replace("##BEHAVIORLEARNING_IMG##", img22));

        //page 8 content
        pages.add("""
<html>
<body style="
    background-color:transparent;
    margin:10px;
    font-family:Arial;
    line-height:1.6;
    max-width:100%;
    overflow-x:hidden;
    color:#222;
">

<h2 style="
    background-color:#2E7D32;
    color:white;
    padding:12px;
    border-radius:10px;
    font-size:18px;
">
Page 8: Innovation & Solutions – Technology, Finance, Governance
</h2>

</div>

<br>

<div style="background-color:white; padding:14px; border-radius:12px;">

<h3 style="color:#1b5e20;">Innovation & Solutions for SDG 13</h3>

<h4>Technology, Finance, Governance</h4>

<p>
Meeting SDG 13 (Climate Action) requires more than awareness. It needs a combination of clean technologies, financial support, and strong governance systems to reduce emissions effectively and fairly.
</p>

</div>

<br>

<div style="background-color:#e8f5e9; padding:14px; border-radius:12px;">

<h3 style="color:#2e7d32;">1. Technological Solutions for Climate Action</h3>

<h4>Renewable Energy & Electrification</h4>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##RENEWABLEENERGY_IMG##' width="320">
</div>

<p>
Clean energy systems such as solar, wind, and hydropower are key to replacing fossil fuels.
</p>

<p>They help:</p>

<ul>
    <li>reduce greenhouse gas emissions,</li>
    <li>support electric transport and industries,</li>
    <li>create long-term sustainable energy systems.</li>
</ul>

<p>
However, renewables often need:
</p>

<ul>
    <li>better energy storage,</li>
    <li>stronger electricity grids,</li>
    <li>and improved infrastructure.</li>
</ul>

<br>

<h4>Carbon Capture and Storage (CCS)</h4>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##CARBONSTORAGE_IMG##' width="320">
</div>

<p>
CCS technologies capture CO₂ from industries and power plants before it enters the atmosphere.
</p>

<p>Key points:</p>

<ul>
    <li>Can capture 80–90% of CO₂ emissions</li>
    <li>Important for hard-to-decarbonize industries like:</li>
    <ul>
        <li>cement</li>
        <li>steel</li>
        <li>chemicals</li>
    </ul>
</ul>

<p>
CCS helps reduce emissions but requires:
</p>

<ul>
    <li>high investment costs,</li>
    <li>safe storage systems,</li>
    <li>strong environmental monitoring.</li>
</ul>

<br>

<h4>Other Supporting Technologies</h4>

<ul>
    <li>Hydrogen energy → supports clean fuel systems</li>
    <li>Energy storage systems → stabilize renewable energy supply</li>
    <li>Advanced membranes & materials → improve CO₂ capture efficiency</li>
</ul>

</div>

<br>

<div style="background-color:#e3f2fd; padding:14px; border-radius:12px;">

<h3 style="color:#1565c0;">2. Green Finance for Climate Action</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##GREENFINANCE_IMG##' width="320">
</div>


<h4>Green Bonds and Investments</h4>

<p>
Green finance helps fund climate-friendly projects such as:
</p>

<ul>
    <li>renewable energy plants,</li>
    <li>low-carbon transport systems,</li>
    <li>sustainable infrastructure.</li>
</ul>

<p>
Green bonds have contributed to:
</p>

<ul>
    <li>large-scale renewable energy development,</li>
    <li>significant CO₂ emission reductions globally.</li>
</ul>

<br>

<h4>Challenges in Developing Countries</h4>

<p>Some barriers include:</p>

<ul>
    <li>limited financial institutions,</li>
    <li>high project costs,</li>
    <li>difficulty accessing large-scale funding.</li>
</ul>

<p>Solutions include:</p>

<ul>
    <li>support from development banks,</li>
    <li>government guarantees,</li>
    <li>international climate funding.</li>
</ul>

<br>

<h4>Impact of Green Finance</h4>

<p>
Countries with stronger green finance systems often show:
</p>

<ul>
    <li>lower carbon emissions,</li>
    <li>faster transition to clean energy,</li>
    <li>stronger climate resilience.</li>
</ul>

</div>

<br>

<div style="background-color:#fff8e1; padding:14px; border-radius:12px;">

<h3 style="color:#ef6c00;">3. Governance and Institutional Support</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##GOVERNMENTSUPPORT_IMG##' width="320">
</div>


<h4>Role of Governments and Institutions</h4>

<p>
Strong governance is essential for climate success.
</p>

<p>It helps:</p>

<ul>
    <li>enforce environmental regulations,</li>
    <li>reduce corruption,</li>
    <li>ensure fair climate policies,</li>
    <li>support renewable energy transition.</li>
</ul>

<p>
Weak governance often leads to:
</p>

<ul>
    <li>slow climate action,</li>
    <li>inefficient policies,</li>
    <li>missed SDG targets.</li>
</ul>

<br>

<h4>Global Climate Governance</h4>

<p>
Climate governance today is:
</p>

<ul>
    <li>complex,</li>
    <li>multi-level (local → global),</li>
    <li>and sometimes fragmented.</li>
</ul>

<p>Challenges include:</p>

<ul>
    <li>unequal climate funding distribution,</li>
    <li>coordination gaps between countries,</li>
    <li>misaligned financial flows.</li>
</ul>

</div>

<br>

<div style="background-color:#ede7f6; padding:14px; border-radius:12px;">

<h3 style="color:#5e35b1;">Summary</h3>

<p>
SDG 13 solutions work best as a combined system:
</p>

<ul>
    <li><b>Technology</b> → renewable energy, CCS, storage systems</li>
    <li><b>Finance</b> → green bonds and climate investments</li>
    <li><b>Governance</b> → strong policies and international cooperation</li>
</ul>

<p>
Climate action is not just about technology alone; it depends on money, policies, and global cooperation working together for a sustainable and fair future.
</p>

</div>

</body>
</html>
""".replace("##RENEWABLEENERGY_IMG##", img23) .replace("##CARBONSTORAGE_IMG##", img24).replace("##GREENFINANCE_IMG##", img31).replace("##GOVERNMENTSUPPORT_IMG##", img32));
        //page 9 content
        pages.add("""

<html>
<body style="
    background-color:transparent;
    margin:10px;
    font-family:Arial;
    line-height:1.6;
    max-width:100%;
    overflow-x:hidden;
    color:#222;
">

<h2 style="
    background-color:#C62828;
    color:white;
    padding:12px;
    border-radius:10px;
    font-size:18px;
">
Page 9: Barriers & Challenges to Achieving SDG 13
</h2>


</div>

<br>

<div style="background-color:#ffffff; padding:14px; border-radius:12px;">

<h3 style="color:#8e2430;">1. Key Barriers</h3>

<h4>Gaps in Political Will and Policy Coherence</h4>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##POLICYCOHERENCE_IMG##' width="320">
</div>


<p>
Many governments acknowledge climate risks but fail to fully “domesticate” SDG 13 into laws, budgets, and sector policies. Lack of political will, weak knowledge among officials, and unsustainable practices slow progress, as seen in Nigeria.
</p>

<p>
At the global level, climate negotiations have become fragmented and finance-centric, with many promises not matched by real emission cuts or policy integration.
</p>

<br>

<h4>Insufficient and Poorly Targeted Finance</h4>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##INSUFFICIENTFINANCE_IMG##' width="320">
</div>


<p>
Climate action in developing countries is expensive, and finding adequate funding to build resilience is a chronic problem.
</p>

<p>
Even though climate finance has grown, fossil fuel subsidies remain huge, and the most climate-vulnerable countries often receive less finance per person, contradicting SDG 13 commitments.
</p>

<p>
Green and sustainable finance tools are growing, but high transaction costs, weak institutions, and market barriers in the Global South limit their use.
</p>

<br>

<h4>Fragmented Governance and Limited Capacity</h4>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##POORCOORDINATION_IMG##' width="320">
</div>


<p>
SDG implementation is hindered by fragmented authority, overlapping mandates, and poor coordination across ministries and levels of government.
</p>

<p>
Local governments often face resource and competency gaps, especially smaller municipalities, which slows progress on SDG 13.
</p>

</div>

<br>

<div style="background-color:#fff3e0; padding:14px; border-radius:12px;">

<h3 style="color:#ef6c00;">2. Need for Systemic Transformation</h3>

<p>
Authors stress that incremental changes are not enough. Achieving SDG 13 by 2030 requires transformative, system-wide changes in energy, food systems, finance, and institutions, rather than small, isolated projects.
</p>

<p>
Transformations must work within existing governance systems at first, but gradually reform them to be more coherent, participatory, and accountable.
</p>

</div>

<br>

<div style="background-color:#e8f5e9; padding:14px; border-radius:12px;">

<h3 style="color:#2e7d32;">3. Equity and Climate Justice</h3>

<p>
Climate impacts fall hardest on people and countries that contributed least to the problem, especially least-developed countries and small island states with low adaptive capacity.
</p>

<p>
Studies show that climate finance does not reliably flow to the most vulnerable, and mitigation measures can create trade-offs that worsen inequality if costs and benefits are unevenly shared.
</p>

<p>
A climate-just approach to SDG 13 means:
</p>

<ul>
    <li>Prioritizing support for highly vulnerable groups and regions.</li>
    <li>Ensuring inclusive participation in decision-making, especially for marginalized and Indigenous communities.</li>
    <li>Designing policies so that mitigation and adaptation also reduce poverty and inequality, instead of reinforcing them.</li>
</ul>

</div>

<br>

<div style="background-color:#ede7f6; padding:14px; border-radius:12px;">

<h3 style="color:#5e35b1;">4. Take-Home Message 🏠</h3>

<p>
SDG 13 faces serious barriers: weak political will, policy incoherence, lack of finance and capacity, and complex, fragmented governance.
</p>

<p>
Overcoming these challenges requires systemic transformation of economies and institutions, as well as a strong focus on equity and climate justice, to ensure that climate action protects and empowers those most affected by climate change.
</p>

</div>

<br>




</div>

</body>
</html>
""".replace("##POLICYCOHERENCE_IMG##", img25) .replace("##INSUFFICIENTFINANCE_IMG##", img26) .replace("##POORCOORDINATION_IMG##", img27));

        //page 10 content
        pages.add("""
<html>
<body style="
    background-color:transparent;
    margin:10px;
    font-family:Arial;
    line-height:1.6;
    max-width:100%;
    overflow-x:hidden;
    color:#222;
">

<h2 style="
    background-color:#2E7D32;
    color:white;
    padding:12px;
    border-radius:10px;
    font-size:18px;
">
Page 10: Taking Action – What Can You Do?
</h2>


</div>

<br>

<div style="background-color:#ffffff; padding:14px; border-radius:12px;">

<h3 style="color:#2e7d32;">Individual Actions That Contribute to SDG 13</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##INDIVIDUACTION_IMG##' width="320">
</div>


<p>
There are many simple actions individuals can take to support climate action and contribute to SDG 13.
</p>

<ul>
    <li>Energy conservation at home, school, and the workplace</li>
    <li>Reducing electricity and fuel consumption</li>
    <li>Supporting renewable energy</li>
    <li>Advocacy and environmental awareness campaigns</li>
    <li>Spreading awareness about climate change</li>
</ul>

</div>

<br>

<div style="background-color:#e3f2fd; padding:14px; border-radius:12px;">

<h3 style="color:#1565c0;">Community Engagement</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##COMMUNITY_IMG##' width="320">
</div>


<p>
Climate action becomes more effective when communities work together.
</p>

<ul>
    <li>Joining local environmental initiatives</li>
    <li>Participating in recycling and sustainability programs</li>
    <li>Influencing policy through civic participation</li>
    <li>Supporting local climate and renewable energy projects</li>
</ul>

</div>

<br>

<div style="background-color:#fff8e1; padding:14px; border-radius:12px;">

<h3 style="color:#ef6c00;">Call to Action</h3>

<div style="text-align:center; margin-top:10px; margin-bottom:15px;">
   <img src='##CALLTOACTION_IMG##' width="320">
</div>


<p>
Every effort counts toward building resilience and achieving global sustainability goals by 2030.
</p>

<p>
Climate action starts with individuals, grows through communities, and requires global cooperation to create a more sustainable future for everyone.
</p>



</div>

<div style="
    margin-top:12px;
    padding:10px;
    background-color:#2E7D32;
    color:white;
    border-radius:10px;
    text-align:center;
    font-weight:bold;
">

Are you ready for the quiz? 🚀<br>
Let’s press Quit to navigate to the Quiz Module ↙

</div>

</body>
</html>
""".replace("##INDIVIDUACTION_IMG##", img28) .replace("##COMMUNITY_IMG##", img29) .replace("##CALLTOACTION_IMG##", img30));

    }


}