package com.shivank.billingsystem;

/**
 * Enum to provide various data sources this application can connect to.
 * And to provide their corresponding SystemManager objects.
 */
public enum Mode {
	XML {
		public ISystemManager getManager(String path) {
			return new XMLSystemManager(path);
		}
	},
	SEEDED {
		public ISystemManager getManager(String path) {
			return new SeededSystemManager();
		}
	};

	/**
	 * Returns System Manager for different data source modes.
	 *  
	 * @param path Path to the data source.
	 * @return System Manager.
	 */
	public abstract ISystemManager getManager(String path);
}
