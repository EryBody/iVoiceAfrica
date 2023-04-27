package com.ivoiceafrica.ivoiceafrica.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.ivoiceafrica.ivoiceafrica.flutterwave.BankBranchResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.BankResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.BankTransferResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.GhsBankTransferRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.GhsBankTransferResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.KesBankTransferRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.KesBankTransferResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomFcmbRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferDomUNFIRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.NgnBankTransferRequest;
import com.ivoiceafrica.ivoiceafrica.flutterwave.ZarBankTransferDomResponse;
import com.ivoiceafrica.ivoiceafrica.flutterwave.ZarBankTransferRequest;

import reactor.core.publisher.Flux;

@Service
public class FlutterwaveService {
	
	@Value("${flutterwave.PBFSecretKey}")
	String SecretKey;
	
	@Value("${flutterwave.baseurl}")
	String baseUrl;

	String SecurityToken = "Bearer "+SecretKey;
	
	String PAYMENT_BASEURI = baseUrl;

	@Autowired
	WebClient webClient;

	@Autowired
	RestTemplate restTemplate;

	public Flux<?> getBankUsingWebFlux(String id) {

		Flux<BankResponse> response = webClient.get().uri(uriBuilder -> uriBuilder.path("/banks/{id}").build(id))
				.header("Authorization", SecurityToken).retrieve().bodyToFlux(BankResponse.class)

				.doOnError(throwable -> System.out.printf("Failed for some reason", throwable));

		return response;
	}

	public BankResponse getBank(String countryCode) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", SecurityToken);

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			String uri = PAYMENT_BASEURI+"/banks/{countryCode}";
			ResponseEntity<BankResponse> response = restTemplate.exchange(uri, HttpMethod.GET, entity,
					BankResponse.class, countryCode);

			// check response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("===>>> Request Successful.");
				System.out.println(response.getBody());

				return response.getBody();
			} else {
				System.out.println("===>>> Request Failed");
				System.out.println(response.getStatusCode());

				return new BankResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return new BankResponse();
		}
	}
	
	
	
	public BankBranchResponse getBankBranch(String bankCode) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", SecurityToken);

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			String uri = PAYMENT_BASEURI+"/banks/{bankCode}/branches";
			ResponseEntity<BankBranchResponse> response = restTemplate.exchange(uri, HttpMethod.GET, entity,
					BankBranchResponse.class, bankCode);

			// check response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("===>>> Request Successful.");
				System.out.println(response.getBody());

				return response.getBody();
			} else {
				System.out.println("===>>> Request Failed");
				System.out.println(response.getStatusCode());

				return new BankBranchResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return new BankBranchResponse();
		}
	}
	
	
	public BankTransferResponse ngnBankTransfer(NgnBankTransferRequest ngnBankTransferRequest) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", SecurityToken);

			HttpEntity<NgnBankTransferRequest> request = new HttpEntity<>(ngnBankTransferRequest, headers);

			String uri = PAYMENT_BASEURI+"/transfers";
			ResponseEntity<BankTransferResponse> response = restTemplate.exchange(uri, HttpMethod.POST, request,
					BankTransferResponse.class);

			// check response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("===>>> Request Successful.");
				System.out.println(response.getBody());

				return response.getBody();
			} else {
				System.out.println("===>>> Request Failed");
				System.out.println(response.getStatusCode());

				return new BankTransferResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return new BankTransferResponse();
		}
	}
	
	
	
	public NgnBankTransferDomResponse ngnDomBankTransfer(NgnBankTransferDomRequest ngnBankTransferDomRequest) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", SecurityToken);

			HttpEntity<NgnBankTransferDomRequest> request = new HttpEntity<>(ngnBankTransferDomRequest, headers);

			String uri = PAYMENT_BASEURI+"/transfers";
			ResponseEntity<NgnBankTransferDomResponse> response = restTemplate.exchange(uri, HttpMethod.POST, request,
					NgnBankTransferDomResponse.class);

			// check response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("===>>> Request Successful.");
				System.out.println(response.getBody());

				return response.getBody();
			} else {
				System.out.println("===>>> Request Failed");
				System.out.println(response.getStatusCode());

				return new NgnBankTransferDomResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return new NgnBankTransferDomResponse();
		}
	}
	
	
	public NgnBankTransferDomResponse ngnDomFcmbBankTransfer(NgnBankTransferDomFcmbRequest ngnBankTransferDomFcmbRequest) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", SecurityToken);

			HttpEntity<NgnBankTransferDomFcmbRequest> request = new HttpEntity<>(ngnBankTransferDomFcmbRequest, headers);

			String uri = PAYMENT_BASEURI+"/transfers";
			ResponseEntity<NgnBankTransferDomResponse> response = restTemplate.exchange(uri, HttpMethod.POST, request,
					NgnBankTransferDomResponse.class);

			// check response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("===>>> Request Successful.");
				System.out.println(response.getBody());

				return response.getBody();
			} else {
				System.out.println("===>>> Request Failed");
				System.out.println(response.getStatusCode());

				return new NgnBankTransferDomResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return new NgnBankTransferDomResponse();
		}
	}
	
	
	public NgnBankTransferDomResponse ngnDomUnfiBankTransfer(NgnBankTransferDomUNFIRequest ngnBankTransferDomUNFIRequest) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", SecurityToken);

			HttpEntity<NgnBankTransferDomUNFIRequest> request = new HttpEntity<>(ngnBankTransferDomUNFIRequest, headers);

			String uri = PAYMENT_BASEURI+"/transfers";
			ResponseEntity<NgnBankTransferDomResponse> response = restTemplate.exchange(uri, HttpMethod.POST, request,
					NgnBankTransferDomResponse.class);

			// check response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("===>>> Request Successful.");
				System.out.println(response.getBody());

				return response.getBody();
			} else {
				System.out.println("===>>> Request Failed");
				System.out.println(response.getStatusCode());

				return new NgnBankTransferDomResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return new NgnBankTransferDomResponse();
		}
	}
	
	

	public GhsBankTransferResponse GhsBankTransfer(GhsBankTransferRequest ghsBankTransferRequest) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", SecurityToken);

			HttpEntity<GhsBankTransferRequest> request = new HttpEntity<>(ghsBankTransferRequest, headers);

			String uri = PAYMENT_BASEURI+"/transfers";
			ResponseEntity<GhsBankTransferResponse> response = restTemplate.exchange(uri, HttpMethod.POST, request,
					GhsBankTransferResponse.class);

			// check response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("===>>> Request Successful.");
				System.out.println(response.getBody());

				return response.getBody();
			} else {
				System.out.println("===>>> Request Failed");
				System.out.println(response.getStatusCode());

				return new GhsBankTransferResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return new GhsBankTransferResponse();
		}
	}
	
	
	public KesBankTransferResponse kesBankTransfer(KesBankTransferRequest kesBankTransferResponse) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", SecurityToken);

			HttpEntity<KesBankTransferRequest> request = new HttpEntity<>(kesBankTransferResponse, headers);

			String uri = PAYMENT_BASEURI+"/transfers";
			ResponseEntity<KesBankTransferResponse> response = restTemplate.exchange(uri, HttpMethod.POST, request,
					KesBankTransferResponse.class);

			// check response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("===>>> Request Successful.");
				System.out.println(response.getBody());

				return response.getBody();
			} else {
				System.out.println("===>>> Request Failed");
				System.out.println(response.getStatusCode());

				return new KesBankTransferResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return new KesBankTransferResponse();
		}
	}
	
	public ZarBankTransferDomResponse zarBankTransfer(ZarBankTransferRequest zarBankTransferRequest) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", SecurityToken);

			HttpEntity<ZarBankTransferRequest> request = new HttpEntity<>(zarBankTransferRequest, headers);

			String uri = PAYMENT_BASEURI+"/transfers";
			ResponseEntity<ZarBankTransferDomResponse> response = restTemplate.exchange(uri, HttpMethod.POST, request,
					ZarBankTransferDomResponse.class);

			// check response
			if (response.getStatusCode() == HttpStatus.OK) {
				System.out.println("===>>> Request Successful.");
				System.out.println(response.getBody());

				return response.getBody();
			} else {
				System.out.println("===>>> Request Failed");
				System.out.println(response.getStatusCode());

				return new ZarBankTransferDomResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return new ZarBankTransferDomResponse();
		}
	}
	

}
