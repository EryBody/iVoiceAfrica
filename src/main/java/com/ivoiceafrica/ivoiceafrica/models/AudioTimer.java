package com.ivoiceafrica.ivoiceafrica.models;

public class AudioTimer {

	private long audioInSecs;
	private long audioInMins;
	private long audioInHours;
	
	public long getAudioInSecs() {
		return audioInSecs;
	}
	public void setAudioInSecs(long audioInSecs) {
		this.audioInSecs = audioInSecs;
	}
	public long getAudioInMins() {
		return audioInMins;
	}
	public void setAudioInMins(long audioInMins) {
		this.audioInMins = audioInMins;
	}
	public long getAudioInHours() {
		return audioInHours;
	}
	public void setAudioInHours(long audioInHours) {
		this.audioInHours = audioInHours;
	}
	
	@Override
	public String toString() {
		return "AudioTimer [audioInSecs=" + audioInSecs + ", audioInMins=" + audioInMins + ", audioInHours="
				+ audioInHours + "]";
	}
	
}
