package extractor;

import model.DepotTransaktion;
import model.Document;
import model.KontoTransaktionDividende;
import model.OrderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.print.Doc;

public class TextExtractorTest {

    @Test
    public void testEinzahlung(){
        String docEinzahlung = "Portfolio 1.111.111.111.01 WWWWWW\n" +
                "Basel, 17.05.2021\n" +
                "Einzahlung 3a\n" +
                "Gutschrift vom 16.05.2021:\n" +
                "Zahlungseingang von: Einzahlung BESR\n" +
                "Referenznummer: 11 111111 00000 11111 00000 11111\n" +
                "Zahlungseingang: Betrag CHF 1.00\n" +
                "Gutschrift: Valuta 17.05.2021 CHF 1.00\n" +
                "S. E. & O.\n" +
                "Freundliche Grüsse\n" +
                "Terzo Vorsorgestiftung\n" +
                "Anzeige ohne Unterschrift\n";
        try{
            Document doc = new TextExtractor(docEinzahlung).getdocument();
            Assertions.assertEquals("01", doc.getPortfolio(),"Portfolio");
            Assertions.assertEquals(OrderType.EINLAGE, doc.getOrderType(),  "Order Type");
            Assertions.assertEquals("1.00", doc.getVerrechneterBetrag(), "Betrag");
            Assertions.assertEquals("CHF", doc.getBuchungsWaehrung(), "Währung");
            Assertions.assertEquals("17.05.2021", doc.getValutaDatum(),"Datum");
        } catch (Exception e) {
            Assertions.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testDividende(){
        String docDividende = "Portfolio 1.123.123.123.03\n" +
                "Dividendenausschüttung\n" +
                "Wir haben für Sie folgende Ausschüttung verbucht:\n" +
                "Dividendenart: Ordentliche Dividende\n" +
                "1.234 Ant UBS ETF MSCI Pacific SRI\n" +
                "ISIN: LU0629460832\n" +
                "Ausschüttung: USD 0.10\n" +
                "Betrag USD 0.12\n" +
                "Umrechnungskurs CHF/USD 0.9024 CHF 0.11\n" +
                "Gutgeschriebener Betrag: Valuta 01.08.2021 CHF 0.11\n" +
                "S. E. & O.";
        try{
            KontoTransaktionDividende doc = (KontoTransaktionDividende) new TextExtractorDividende(docDividende).getdocument();
            Assertions.assertEquals("03",doc.getPortfolio(),"Portfolio");
            Assertions.assertEquals( OrderType.DIVIDENDE,doc.getOrderType(), "Order Type");
            Assertions.assertEquals("Ordentliche Dividende",doc.getDividendenart(),"Dividendenart");
            Assertions.assertEquals("LU0629460832",doc.getIsin(),"ISIN");
            Assertions.assertEquals("0.10",doc.getAusschuettung(),"Ausschüttung Betrag");
            Assertions.assertEquals("USD",doc.getAusschuettungWaehrung(),"Währung Ausschüttung");
            Assertions.assertEquals("0.12",doc.getBetrag(), "Betrag");
            Assertions.assertEquals("USD",doc.getBetragsWaehrung(),  "Buchungswähurng");
            Assertions.assertEquals("0.11",doc.getVerrechneterBetrag(), "Gutgeschriebener Betrag");
            Assertions.assertEquals("CHF",doc.getBuchungsWaehrung(),  "Gutgeschriebener Betrag Währung");
            Assertions.assertEquals("0.9024",doc.getUmrechnungskurs(),  "Umrechnungskurs");
            Assertions.assertEquals("01.08.2021",doc.getValutaDatum(),"Datum");
        } catch (Exception e) {
            Assertions.fail();
            e.printStackTrace();
        }

    }

    @Test
    public void testVerwaltungsgebuehr(){
        String docGebuehr = "Terzo Vorsorgestiftung der WIR Bank\n" +
                "Portfolio 3.333.333.333.03\n" +
                "Basel, 01.10.2021\n" +
                "Belastung\n" +
                "Am 30.09.2021 haben wir Ihr Konto belastet:\n" +
                "Effektive VIAC Verwaltungsgebühr p.a. (berechnet auf Portfolioebene): 0.44%\n" +
                "Durchschnittliches Vorsorgevermögen: CHF 111.11\n" +
                "Abzüglich 3a Kontoguthaben (Liquidität): CHF 11.11\n" +
                "Abzüglich befreite Anlagen (Gebührencap): CHF 1.11\n" +
                "Abzüglich Freibeträge CHF -\n" +
                "Effektive Berechnungsbasis: Monat September CHF 100.00\n" +
                "Verrechneter Betrag: Valuta 30.09.2021 CHF -0.20\n" +
                "S. E. & O.\n" +
                "Berechnung: Die effektive VIAC Verwaltungsgebühr leitet sich vom durchschnittlichen Vorsorgevermögen abzüglich Kontoguthaben sowie unter der \n" +
                "Berücksichtigung des Gebührencaps von 0.44% ab. Davon werden zusätzlich allfällige Freibeträge abgezogen. Basis für die Berechnung ist die VIAC \n" +
                "Verwaltungsgebühr von 0.52% p.a., welche auf maximal 84.62%  investiertem Vermögen belastet wird. Anlagen darüber werden durch den \n" +
                "Gebührencap kostenlos verwaltet.\n" +
                "Hinweis: Wo immer möglich übernimmt die Stiftung selbst die Produktkosten (Indexfonds). Bei den ETFs werden die Produktkosten direkt innerhalb \n" +
                "der Anlagen zu Gunsten der Fondsanbieter belastet. Diese fallen zusätzlich zu der Verwaltungsgebühr an und betragen für die aktuell gewählte \n" +
                "Strategie 0.09% pro Jahr. VIAC erhält keinerlei Rückvergütungen (Retrozessionen).\n" +
                "Freundliche Grüsse\n" +
                "Terzo Vorsorgestiftung\n" +
                "Anzeige ohne Unterschrift\n";

        try{
            Document doc = new TextExtractor(docGebuehr).getdocument();
            Assertions.assertEquals("03", doc.getPortfolio(),"Portfolio");
            Assertions.assertEquals(OrderType.VERWALTUNGSGEBUEHREN, doc.getOrderType(),  "Order Type");
            Assertions.assertEquals("-0.20", doc.getVerrechneterBetrag(), "Betrag");
            Assertions.assertEquals("CHF", doc.getBuchungsWaehrung(), "Währung");
            Assertions.assertEquals("30.09.2021", doc.getValutaDatum(),"Datum");
        }catch (Exception e) {
            Assertions.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testZins(){
        String docZins = "Terzo Vorsorgestiftung der WIR Bank\n" +
                "Auberg 1\n" +
                "4002 Basel\n" +
                "Internet www.viac.ch\n" +
                "E-Mail info@viac.ch\n" +
                "Portfolio 2.222.222.222.02\n" +
                "Basel, 01.10.2021\n" +
                "Zins\n" +
                "Am 30.09.2021 haben wir Ihrem Konto gutgeschrieben:\n" +
                "Zinssatz: 0.10%\n" +
                "Zinsperiode: September\n" +
                "Zinsgutschrift: CHF 0.01\n" +
                "Verrechneter Betrag: CHF 0.01\n" +
                "S. E. & O.\n" +
                "Freundliche Grüsse\n" +
                "Terzo Vorsorgestiftung\n";
        try{
            Document doc = new TextExtractorZins(docZins).getdocument();
            Assertions.assertEquals("02", doc.getPortfolio(),"Portfolio");
            Assertions.assertEquals(OrderType.ZINS, doc.getOrderType(),  "Order Type");
            Assertions.assertEquals("0.01", doc.getVerrechneterBetrag(), "Betrag");
            Assertions.assertEquals("CHF", doc.getBuchungsWaehrung(), "Währung");
            Assertions.assertEquals("30.09.2021", doc.getValutaDatum(),"Datum");
        }catch (Exception e) {
            Assertions.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testKauf(){
        String docKauf = "Terzo Vorsorgestiftung der WIR Bank\n" +
                "Portfolio 1.111.111.111.01\n" +
                "Basel, 02.09.2021\n" +
                "Börsenabrechnung - Kauf\n" +
                "Wir haben für Sie folgenden Auftrag ausgeführt:\n" +
                "Order: Kauf\n" +
                "0.123 Ant UBS ETF MSCI Emerging Markets SRI\n" +
                "ISIN: LU1048313891\n" +
                "Kurs: USD 16.45\n" +
                "Betrag USD 2.02\n" +
                "Umrechnungskurs CHF/USD 0.91603 CHF 1.85\n" +
                "Stempelsteuer CHF 0.01\n" +
                "Verrechneter Betrag: Valuta 02.09.2021 CHF 1.86\n" +
                "S. E. & O.\n" +
                "Freundliche Grüsse\n" +
                "Terzo Vorsorgestiftung\n" +
                "Anzeige ohne Unterschrift\n";


        try{
            DepotTransaktion doc = (DepotTransaktion) new TextExtractor(docKauf).getdocument();
            Assertions.assertEquals("01",doc.getPortfolio(),"Portfolio");
            Assertions.assertEquals(0.123,(double) Math.round(doc.getAnzahl() * 1000d) / 1000d,"Anzahl");
            Assertions.assertEquals("LU1048313891",doc.getIsin(),"ISIN");
            Assertions.assertEquals("16.45",doc.getAktienKurs(),"Aktienkurs");
            Assertions.assertEquals("USD",doc.getKursWaehrung(),"Aktienkurs Währung");
            Assertions.assertEquals("2.02",doc.getBetrag(),"Betrag");
            Assertions.assertEquals("USD",doc.getBetragsWaehrung(),"Betrag Waherung");
            Assertions.assertEquals("0.91603",doc.getUmrechnungskurs(),"Umrechnungskurs");
            Assertions.assertEquals("0.01",doc.getSteuern(),"Steuern");
            Assertions.assertEquals("1.86",doc.getVerrechneterBetrag(),"Verrechneter Betrag");
            Assertions.assertEquals("02.09.2021",doc.getValutaDatum(),"Datum");
        } catch (Exception e) {
            Assertions.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testVerkauf(){
        String docText = "Portfolio 3.333.333.333.03\n" +
                "Basel, 31.12.2021\n" +
                "Börsenabrechnung - Verkauf\n" +
                "Wir haben für Sie folgenden Auftrag ausgeführt:\n" +
                "Order: Verkauf\n" +
                "0.013 Ant UBS ETF MSCI Pacific SRI\n" +
                "ISIN: LU0629460832\n" +
                "Kurs: USD 84.44\n" +
                "Betrag USD 1.11\n" +
                "Umrechnungskurs CHF/USD 0.92048 CHF 0.85\n" +
                "Stempelsteuer CHF -0.00\n" +
                "Verrechneter Betrag: Valuta 31.12.2021 CHF 1.00";


        try{
            DepotTransaktion doc = (DepotTransaktion) new TextExtractor(docText).getdocument();
            Assertions.assertEquals("03",doc.getPortfolio(),"Portfolio");
            Assertions.assertEquals(0.013,(double) Math.round(doc.getAnzahl() * 1000d) / 1000d,"Anzahl");
            Assertions.assertEquals("LU0629460832",doc.getIsin(),"ISIN");
            Assertions.assertEquals("84.44",doc.getAktienKurs(),"Aktienkurs");
            Assertions.assertEquals("USD",doc.getKursWaehrung(),"Aktienkurs Währung");
            Assertions.assertEquals("1.11",doc.getBetrag(),"Betrag");
            Assertions.assertEquals("USD",doc.getBetragsWaehrung(),"Betrag Waherung");
            Assertions.assertEquals("0.92048",doc.getUmrechnungskurs(),"Umrechnungskurs");
            Assertions.assertEquals("-0.00",doc.getSteuern(),"Steuern");
            Assertions.assertEquals("1.00",doc.getVerrechneterBetrag(),"Verrechneter Betrag");
            Assertions.assertEquals("31.12.2021",doc.getValutaDatum(),"Datum");
        } catch (Exception e) {
            Assertions.fail();
            e.printStackTrace();
        }
    }
}
