package com.hs.spring_ai_media.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	private TranscriptionModel transcriptionModel;

	@Autowired
	private OpenAiAudioSpeechModel speechModel;

	@Value("${classpath:sample2.m4a}")
	private Resource inputAudio;

	@Override
	public String convertAudioToText(Resource inputAudio) {
		return transcriptionModel.transcribe(inputAudio);

	}

	@Override
	public String convertAudioToTextWithOptions(Resource inputAudio) {
		return transcriptionModel.transcribe(inputAudio, OpenAiAudioTranscriptionOptions.builder().language("en")
				.temperature(0.7f).prompt("Spring boot ").build());
	}

	@Override
	public byte[] convertTextToAudio(Resource resource) throws IOException {
		String input;
		try (InputStream is = resource.getInputStream()) {
			input = new String(is.readAllBytes(), StandardCharsets.UTF_8);
		}

		OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
				.voice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY).speed(1.0d)
				.responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
				.model(OpenAiAudioApi.TtsModel.TTS_1.value).build();

		TextToSpeechPrompt speechPrompt = new TextToSpeechPrompt(input, speechOptions);
		TextToSpeechResponse response = speechModel.call(speechPrompt);
		return response.getResult().getOutput();
	}

}
