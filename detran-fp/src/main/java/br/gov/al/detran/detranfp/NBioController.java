package br.gov.al.detran.detranfp;

import javax.inject.Provider;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
@RequestMapping("/nbio")
public class NBioController {

	@Autowired
	private Provider<NBioCapture> nbioProvider;

	/**/
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@GetMapping(value = "/capture")
	@CrossOrigin
	public ResponseEntity<Response> capture() throws NBioBSPJNIException {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		NBioCapture nbio = nbioProvider.get();

		String fp = nbio.captureIMG();

		Response r = new Response(HttpStatus.OK, fp);

		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/json")).body(r);

	}


	@PostMapping(value = "/verify-fp", produces = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin
	public ResponseEntity<Response> captureVerify(@RequestParam("fingerPrint") String fingerPrint)
			throws NBioBSPJNIException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		Response r = null;
		try {

			NBioCapture nbio = nbioProvider.get();

			if (nbio.verificar(fingerPrint)) {
				r = new Response(HttpStatus.OK, "Biometria conferida com sucesso!");
			} else {
				r = new Response(HttpStatus.NOT_FOUND, "Biometria nao confere!");

			}

		} catch (NBioBSPJNIException e) {
			r = new Response(e.getStatus(), e.getMessage());

		}
		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/json")).body(r);
	}

	@GetMapping(value = "/check-scanner", produces = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin
	public ResponseEntity<Response> captureVerify() throws NBioBSPJNIException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		Response r = null;
		try {

			NBioCapture nbio = nbioProvider.get();
			nbio.getBsp();

			r = new Response(HttpStatus.OK, "Biometria conferida com sucesso!");

		} catch (NBioBSPJNIException e) {
			r = new Response(e.getStatus(), e.getMessage());
		}

		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/json")).body(r);

	}

	@GetMapping(value = "/validate-bio", produces = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin
	ResponseEntity<Response> validateBio(@RequestParam("cpf") String cpf,
			@RequestParam(name = "matricula", required = false) String matricula)
			throws JSONException, NBioBSPJNIException {

		Response r = null;

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		String uriString = UriComponentsBuilder
				.fromUriString(env.getProperty("detran.api.host") + "/api/v1/e-clinicas/get-biometria/")
				.queryParam("cpf", cpf).queryParam("matricula", matricula).toUriString();

		NBioCapture nbio = nbioProvider.get();

		String result = restTemplate.getForObject(uriString, String.class);

		JSONObject json = new JSONObject(result);

		JSONObject bio = json.getJSONObject("BIOMETRIA");
		String digital = bio.getString("BIOMETRIA");

		r = new Response(HttpStatus.ACCEPTED);

		if ("".equals(digital)) {
			r.setCode(Integer.toString(HttpStatus.NOT_FOUND.value()));
			r.setMessage("Nao ha registro de biometria para este usuário " + cpf);
		}

		if (nbio.verificar(digital)) {
			r.setCode(Integer.toString(HttpStatus.OK.value()));
			r.setMessage("Digital conferida com sucesso!");
		} else {
			r.setCode(Integer.toString(HttpStatus.NOT_FOUND.value()));
			r.setMessage("Digital nao confere!");
		}

		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/json")).body(r);

	}

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

}
