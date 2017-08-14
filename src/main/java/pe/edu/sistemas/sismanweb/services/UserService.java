package pe.edu.sistemas.sismanweb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.UsuarioDAO;
import pe.edu.sistemas.sismanweb.entidades.Persona;
import pe.edu.sistemas.sismanweb.entidades.Usuario;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UsuarioDAO usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Usuario> usuarioRoles = usuarioDao.obtenerUsuarioxCodSis(username);
		List<GrantedAuthority> authorities = buildAuthorities(usuarioRoles);
		Persona usuario = usuarioRoles.get(0).getPersona();
		User user = new User(usuario.getPersonaCodigoSistema()
				, usuario.getPersonaPasswordSistema(), true, true, true, true, authorities);
		//close session usuarioDao
		return user;
	}
	
	private List<GrantedAuthority> buildAuthorities(List<Usuario> usuarioRoles){
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for(Usuario usu : usuarioRoles){
			roles.add(new SimpleGrantedAuthority(usu.getTipoUsuario().getNombreTipoUsuario()));
		}
		return roles;
	}

}
