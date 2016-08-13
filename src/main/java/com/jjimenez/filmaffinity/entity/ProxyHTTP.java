package com.jjimenez.filmaffinity.entity;

/**
 * Entity with the necessaries params to connect through HTTPProxy
 * 
 * @author Jesus Jimenez
 * @since 0.1.0
 */
public class ProxyHTTP {

	private String user;
	private String password;
	private String host;
	private String port;
	
	public ProxyHTTP(String host, String port) {
		super();
		this.host = host;
		this.port = port;
	}

	public ProxyHTTP(String host, String port,String user, String password) {
		super();
		this.user = user;
		this.password = password;
		this.host = host;
		this.port = port;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProxyHTTP other = (ProxyHTTP) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProxyObject [user=" + user + ", password=" + password + ", host=" + host + ", port=" + port + "]";
	}	
	
}
