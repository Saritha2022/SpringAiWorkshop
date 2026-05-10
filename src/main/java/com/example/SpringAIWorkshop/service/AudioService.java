package com.example.SpringAIWorkshop.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.stereotype.Service;

@Service
public class AudioService {
	
	OpenAiAudioSpeechModel audioModel;
	public AudioService(OpenAiAudioSpeechModel audioModel) {
		this.audioModel = audioModel;
	}
	
	
	public String convertTextToSpeech(String text) {
		
		OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
			    .model("gpt-4o-mini-tts")
			    .voice(OpenAiAudioApi.SpeechRequest.Voice.BALLAD)
			    .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
			    .speed(1.0) //double in latest code
			    .build();
		
		TextToSpeechPrompt speechPrompt = new TextToSpeechPrompt(text, speechOptions);
		TextToSpeechResponse response = audioModel.call(speechPrompt);
		
		byte[] audio = response.getResult().getOutput();
		
		try (FileOutputStream f = new FileOutputStream("May08Audio.mp3")) {
			f.write(audio);
			System.out.println("Audio file saved as output.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Audio generation successful. Check mp3 file.";
	}
}
