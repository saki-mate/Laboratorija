package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.stereotype.Service;

import com.example.demo.repository.Laboratorija_korisnikReposiroty;

import model.Laboratorija_korisnik;

	@Service("customUserDetailsService")
	public class CustomUserDetailsService implements UserDetailsService{
	 
	    @Autowired
	    Laboratorija_korisnikReposiroty kr;  
	    
	    @Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    	Laboratorija_korisnik user = kr.findBybrLicneKarte(username).get();
	    	System.out.println(user.getBrLicneKarte());
	    	
			MyUserDetails userDetails =new MyUserDetails();
			userDetails.setUsername(user.getBrLicneKarte());
			userDetails.setPassword(user.getSifra());

			userDetails.setUloga(user.getUloga());
			
			return userDetails;
			
	    }
	}