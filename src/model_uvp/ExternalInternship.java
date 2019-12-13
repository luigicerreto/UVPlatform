package model_uvp;

import java.text.SimpleDateFormat;

public class ExternalInternship {
		private int id_ie;
		private String name;
		private int duration_convention;
		private SimpleDateFormat date_convention;
		private int availability;
		private String info;
		
		public ExternalInternship() {
			
		}
		
		public ExternalInternship(int id_ie, String name, int duration_convention, SimpleDateFormat date_convention,
				int availability, String info) {
			this.id_ie = id_ie;
			this.name = name;
			this.duration_convention = duration_convention;
			this.date_convention = date_convention;
			this.availability = availability;
			this.info = info;
		}

		public int getId_ie() {
			return id_ie;
		}

		public void setId_ie(int id_ie) {
			this.id_ie = id_ie;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getDuration_convention() {
			return duration_convention;
		}

		public void setDuration_convention(int duration_convention) {
			this.duration_convention = duration_convention;
		}

		public SimpleDateFormat getDate_convention() {
			return date_convention;
		}

		public void setDate_convention(SimpleDateFormat date_convention) {
			this.date_convention = date_convention;
		}

		public int getAvailability() {
			return availability;
		}

		public void setAvailability(int availability) {
			this.availability = availability;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}
		
}
