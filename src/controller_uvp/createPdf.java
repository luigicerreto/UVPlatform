package controller_uvp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.itextpdf.html2pdf.HtmlConverter;

/**
 * Servlet implementation class createPdf
 */
@WebServlet("/createPdf")
public class createPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public createPdf() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String myvar = "<!DOCTYPE html>"+
				"<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"\" xml:lang=\"\">"+
				"<head>"+
				"<title></title>"+
				""+
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"+
				" <br/>"+
				"<style type=\"text/css\">"+
				"	body {font-family: Garamond, serif;}"+
				"	p {margin:0 auto;padding:0;position:relative;margin-top:10px;white-space:nowrap;}"+
				"	.center{text-align: center;}"+
				"	.inline{width: 40%;}"+
				"	.heading{font-size:19px;color:#000080;}"+
				"	.ft12{font-size:16px;color:#000080;}"+
				"	.ft13{font-size:16px;color:black;}"+
				"	.ft16{font-size:16px;color:black;}"+
				"	.ft25{font-size:14px;color:black;}"+
				"	.ft27{font-size:16px;color:black;}"+
				"	.ft34{font-size:16px;color:#0000ff;}"+
				"	.ft43{font-size:8px;color:black;}"+
				"	.ft44{font-size:7px;color:black;}"+
				"	.ft45{font-size:12px;color:black;}"+
				"	.ft48{font-size:12px;color:black;}"+
				"</style>"+
				"</head>"+
				"<body>"+
				"<div id=\"page1-div\">"+
				"<p class=\"heading center\"><b>U N I V E R S I T À   D E G L I   S T U D I   D I   S A L E R N O </b></p>"+
				"<p class=\"ft12 center\"><b>DIPARTIMENTO DI INFORMATICA</b></p>"+
				"<p class=\"ft12\"> </p>"+
				"<p class=\"ft13 center\"><b>PROGETTO FORMATIVO E DI ORIENTAMENTO </b></p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13 center\"><b>LAUREA TRIENNALE / LAUREA MAGISTRALE </b></p>"+
				"<p class=\"ft10\"> </p>"+
				"<p class=\"ft13\"><b>Relativo alla Convenzione per tirocinio di formazione ed orientamento  (curriculare) stipulata </b></p>"+
				"<p class=\"ft13\"><b>con ____________________________ in data _________________, Repertorio N. _______   </b></p>"+
				"<p class=\"ft10\"> </p>"+
				"<p class=\"ft13\"><b>SOGGETTO PROMOTORE </b></p>"+
				"<p class=\"ft13\">Dipartimento di Informatica dell’Università degli Studi di Salerno; </p>"+
				"<p class=\"ft16\">Sede in Via Giovanni Paolo II, 132, 84084 Fisciano (Salerno) <br/>Indirizzo PEC ammicent@pec.unisa.it </p>"+
				"<p class=\"ft13\">Codice Fiscale 80018670655 </p>"+
				"<p class=\"ft13\">Rappresentante legale: prof. Alfredo De Santis, in qualità di Direttore pro tempore, nato a nato a Nocera </p>"+
				"<p class=\"ft13\">Inferiore (SA) il 07/12/1960. </p>"+
				"<p class=\"ft10\"> </p>"+
				"<p class=\"ft13\"><b>SOGGETTO OSPITANTE</b></p>"+
				"<p class=\"ft13 inline\">Denominazione (specificare la natura giuridica)</p>"+
				"<p class=\"ft13 inline\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13 inline\"><b>Sede legale</b></p>"+
				"<p class=\"ft13 inline\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13 inline\"><b>Indirizzo PEC</b></p>"+
				"<p class=\"ft13 inline\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13 inline\">Codice Fiscale e Partita IVA</p>"+
				"<p class=\"ft13 inline\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\">Rappresentante legale:  _________________________, in qualità di __________, nato a _________  </p>"+
				"<p class=\"ft13\">il _______________ </p>"+
				"<p class=\"ft13\">Attività economica esercitata</p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\">Codice ATECO</p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\">Numero Dipendenti a tempo indeterminato ________________</p>"+
				"<p class=\"ft13\"><b> </b></p>"+
				"<p class=\"ft13\"><b>TIROCINANTE</b></p>"+
				"<p class=\"ft13\">Cognome e nome del tirocinante</p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\">Data e luogo di nascita</p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\">Cittadinanza</p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\">Residenza</p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\">Codice Fiscale</p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\">Telefono n.</p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\">Indirizzo e-mail</p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft16\">Iscritto al Corso di Laurea Triennale in Informatica<br/></p>"+
				"<p class=\"ft25\"><b>TUTOR DESIGNATO DAL DIPARTIMENTO: __________________________________________ <br/> <br/>TUTOR DESIGNATO DAL SOGGETTO OSPITANTE: ___________________________________ <br/></b>Tel. __________________________ <br/>email _________________________ <br/><b> </b></p>"+
				"<p class=\"ft13\"><b>N. TOTALE DI CREDITI FORMATIVI PREVISTI PER L’ATTIVITÀ DI TIROCINIO: </b>____ di cui</p>"+
				"<p class=\"ft27\">[] ___ CFU per tirocinio curriculare <br/>[] ___ CFU provenienti da tirocinio esterno (1) a scelta <br/>[] ___ CFU provenienti da tirocinio esterno (2) a scelta </p>"+
				"<p class=\"ft13\"><b> </b></p>"+
				"<p class=\"ft13\"><b>SEDE DI SVOLGIMENTO DEL TIROCINIO</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13\"><b>INDICAZIONE DEGLI OBIETTIVI</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>INDICAZIONE LE COMPETENZE DA ACQUISIRE</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>INDICAZIONE DELLE ATTIVITÀ FORMATIVE PREVISTE</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft25\"><b> <br/> </b></p>"+
				"<p class=\"ft13\"><b>INDICAZIONE DELLE MODALITÀ DI SVOLGIMENTO DEL TIROCINIO  </b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b> </b></p>"+
				"<p class=\"ft13\"><b>DURATA DEL TIROCINIO</b></p>"+
				"<p class=\"ft13\"><b>:</b> n. ________ mesi, a decorrere dal ________ e fino al ______________ </p>"+
				"<p class=\"ft13\">  </p>"+
				"<p class=\"ft13\"><b>INDICAZIONE DELL’ORARIO DI SVOLGIMENTO DEL TIROCINIO </b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b>________________________________________________________________________________</b></p>"+
				"<p class=\"ft13\"><b> </b></p>"+
				"<p class=\"ft13\"><b>POLIZZE ASSICURATIVE: </b></p>"+
				"<p class=\"ft13\">Posizione assicurativa INAIL: Gestione per conto dello Stato </p>"+
				"<p class=\"ft16\">Polizza assicurativa RC             ______________________________________________________ <br/>Polizza assicurativa Infortuni   _______________________________________________________ </p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13\">Ai  sensi  dell’art.5  della  convenzione  Rep.n.___________,  a  cui  fa  riferimento  il  presente  progetto </p>"+
				"<p class=\"ft16\">formativo, il Soggetto ospitante, in caso di infortunio del tirocinante durante lo svolgimento del tirocinio,  <br/>si  impegna  a  segnalare  tempestivamente  l’evento  al  Dipartimento  di  Informatica  e  al  Responsabile </p>"+
				"<p class=\"ft13\">dell’Ufficio  Stato  Giuridico  e  Formazione  dell’Università,  al  fine  di  consentire  a  quest’ultimo  di </p>"+
				"<p class=\"ft13\">trasmettere la denuncia di infortunio all'INAIL in via telematica entro i tempi previsti dalla normativa </p>"+
				"<p class=\"ft16\">vigente (48 ore). <br/>Il Responsabile pro tempore dell’Ufficio Stato Giuridico e Formazione dell’Ateneo è il dott. Pasquale </p>"+
				"<p class=\"ft13\">Talarico, di cui si indicano di seguito il recapito telefonico e gli indirizzi e-mail a cui far pervenire la </p>"+
				"<p class=\"ft13\">segnalazione dell’infortunio con copia della convenzione e del progetto formativo.  </p>"+
				"<p class=\"ft16\">Inoltre all’Ufficio Stato Giuridico e Formazione vanno trasmessi, a cura del tirocinante, una copia del <br/>certificato  medico  di  infortunio  lavorativo  e  una  relazione  scritta  sulle  modalità  in  cui  è  avvenuto </p>"+
				"<p class=\"ft13\">l’infortunio  (orario  dell’infortunio,  data  e  ora  di  abbandono  del  posto  del  di  lavoro,  attività  svolta  in </p>"+
				"<p class=\"ft13\">occasione  dell’infortunio  e  cause  dello  stesso).  Tale  documentazione  deve  essere  trasmessa  con  la </p>"+
				"<p class=\"ft13\">massima tempestività per le vie brevi oppure tramite e-mail. </p>"+
				"<p class=\"ft13\">Ufficio Stato Giuridico e Formazione </p>"+
				"<p class=\"ft13\">Tel. 089 96 6204 </p>"+
				"<p class=\"ft13\">e-mail<a href=\"mailto:p.talarico@unisa.it\"></a></p>"+
				"<p class=\"ft34\"><a href=\"mailto:p.talarico@unisa.it\">p.talarico@unisa.it </a></p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft25\"><b>EVENTUALI INDENNITÀ DI PARTECIPAZIONE ED EVENTUALI ALTRE AGEVOLAZIONI <br/>DI ALTRA NATURA (solo se previsti) </b>  </p>"+
				"<p class=\"ft16\">• Euro __________ (________________________________ in lettere) mensili;  <br/>• rimborso spese documentate (vitto, trasporto, altro);  </p>"+
				"<p class=\"ft13\">• ticket restaurant;  </p>"+
				"<p class=\"ft13\">• altro (specificare) ___________________________ </p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13\"><b>OBBLIGHI DEL TIROCINANTE </b></p>"+
				"<p class=\"ft13\">-Svolgere  le  attività  previste  dal  presente  progetto  formativo  e  di  orientamento,  rispettando l’ambiente di lavoro;</p>"+
				"<p class=\"ft13\">-Seguire  le  indicazioni  dei  tutori  e  fare  riferimento  ad  essi  per  qualsiasi  esigenza  di  tipo organizzativo o altre evenienze; </p>"+
				"<p class=\"ft13\">-Rispettare gli orari e le regole di comportamento concordati nel presente progetto;</p>"+
				"<p class=\"ft13\">-Rispettare i regolamenti interni e le norme disciplinari in uso presso il soggetto ospitante; </p>"+
				"<p class=\"ft13\">-Rispettare le norme in materia di igiene, sicurezza e salute sui luoghi di lavoro; </p>"+
				"<p class=\"ft13\">-Rispettare gli obblighi di riservatezza, sia durante che dopo lo svolgimento del tirocinio per quanto attiene ai dati, alle informazioni o a tutto quanto acquisito in termini di conoscenze in merito a processi </p>"+
				"<p class=\"ft16\">produttivi e prodotti/servizi dell’azienda ospitante. <br/> </p>"+
				"<p class=\"ft25\"><b>AUTORIZZAZIONE  AL  TRATTAMENTO  DEI  DATI  PERSONALI  ED  AZIENDALI  ED <br/>ASSUNZIONE DI RESPONSABILITÀ</b>: </p>"+
				"<p class=\"ft16\">Con la sottoscrizione del presente progetto si autorizza il trattamento dei dati personali e del soggetto <br/>ospitante ai sensi e per gli effetti del Decreto Legislativo 30 giugno 2003, n. 196. </p>"+
				"<p class=\"ft13\">Agli effetti delle vigenti leggi e nella consapevolezza delle conseguenze penali connesse a dichiarazioni </p>"+
				"<p class=\"ft13\">mendaci, si dichiara che tutti i dati sopra riportati sono veri. </p>"+
				"<p class=\"ft16\">Fisciano, ___________________________ </p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13\">Firma e timbro del soggetto promotore _________________________________________________ </p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13\">Firma del tutore designato dal soggetto promotore</p>"+
				"<p class=\"ft43\">1</p>"+
				"<p class=\"ft13\">________________________________________ </p>"+
				"<p class=\"ft44\">1</p>"+
				"<p class=\"ft45\"> Si ritiene opportuno far firmare il progetto anche ai tutori designati dal soggetto promotore e dal soggetto ospitante, </p>"+
				"<p class=\"ft48\">anche se non è obbligatorio, in modo tale che anch’essi siano a conoscenza sin dall’inizio del contenuto del progetto <br/>stesso, evitando successive comunicazioni. </p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13\">Firma e timbro del soggetto ospitante___________________________________________________ </p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13\">Firma del tutore designato dal soggetto ospitante__________________________________________ </p>"+
				"<p class=\"ft13\"> </p>"+
				"<p class=\"ft13\">Firma per presa visione ed accettazione del tirocinante______________________________________ </p>"+
				"</div>"+
				"</body>"+
				"</html>";

		OutputStream out = response.getOutputStream();
		HtmlConverter.convertToPdf(myvar, out);
		out.close();
	}
}
