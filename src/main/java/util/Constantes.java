package util;

public class Constantes {

	private static String APP_URL;
	private static String ADM_URL;
	private static String APP_ASSETS_URL;
	private static String APP_JS_URL;
	private static String APP_IMG_URL;
	private static String APP_CSS_URL;
	private static Integer NUMBER_OF_ROWS_PER_PAGE;
	private static String SESSION_MSG;
	private static String DATABASE_CONF_DIR;
	private static String EMAIL_CONF_DIR;
	private static String MODULES_IMAGES_DIR;
	private static String USER_PROFILE_IMAGES_DIR;
	private static String USER_PROFILE_NONE_IMAGE_DIR;
	private static String APP_IMG_USER_DIR;
	private static String APP_GUARDIAO_URL;
	private static String THIS_APP_DATABASE_SCHEMA;
	private static String PUBLIC_DATABASE_SCHEMA;
	private static String TEMP_PDF_SOLICITACAO;
	private static String LOGO_UFC;
	
	private Constantes() {
		//
	}

	static {
		DATABASE_CONF_DIR = "C:\\n2s\\bd.txt";
		EMAIL_CONF_DIR = "C:\\n2s\\email.txt";
		APP_URL = "/digital-valley-m2c";
		setAPP_GUARDIAO_URL("/Controle_de_Acesso");
		ADM_URL = APP_URL + "/view/adm";
		APP_ASSETS_URL = APP_URL + "/assets2";
		APP_JS_URL = APP_ASSETS_URL + "/js";
		APP_IMG_URL = APP_ASSETS_URL + "/img";
		//APP_IMG_USER_DIR ="C:\\n2s\\img\\";
		APP_CSS_URL = APP_ASSETS_URL + "/css";
		NUMBER_OF_ROWS_PER_PAGE = 20;
		SESSION_MSG = "msg";
		MODULES_IMAGES_DIR = "C:\\imagens\\modulo";
		USER_PROFILE_IMAGES_DIR = "C:\\n2s\\img\\";
		USER_PROFILE_NONE_IMAGE_DIR =  USER_PROFILE_IMAGES_DIR + "none.png";
		THIS_APP_DATABASE_SCHEMA="ctrl-acesso";
		PUBLIC_DATABASE_SCHEMA="public";
		TEMP_PDF_SOLICITACAO="C:\\Users\\Matheus Diógenes\\solici.pdf";
		LOGO_UFC = "C:\\imagens\\ufc.jpg";
	}

	/**
	 * @return the appUrl
	 */
	public static String getAppUrl() {
		return APP_URL;
	}

	/**
	 * @return the admUrl
	 */
	public static String getAdmUrl() {
		return ADM_URL;
	}

	/**
	 * @return the appAssetsUrl
	 */
	public static String getAppAssetsUrl() {
		return APP_ASSETS_URL;
	}

	/**
	 * @return the appJsUrl
	 */
	public static String getAppJsUrl() {
		return APP_JS_URL;
	}

	/**
	 * @return the appImgUrl
	 */
	public static String getAppImgUrl() {
		return APP_IMG_URL;
	}

	/**
	 * @return the appCssUrl
	 */
	public static String getAppCssUrl() {
		return APP_CSS_URL;
	}

	/**
	 * @return the numberOfRowsPerPage
	 */
	public static Integer getNumberOfRowsPerPage() {
		return NUMBER_OF_ROWS_PER_PAGE;
	}

	/**
	 * @return the sessionMsg
	 */
	public static String getSessionMsg() {
		return SESSION_MSG;
	}

	/**
	 * @return the databaseConfDir
	 */
	public static String getDatabaseConfDir() {
		return DATABASE_CONF_DIR;
	}

	/**
	 * @return the emailConfDir
	 */
	public static String getEmailConfDir() {
		return EMAIL_CONF_DIR;
	}

	/**
	 * @return the mODULES_IMAGES_DIR
	 */
	public static String getMODULES_IMAGES_DIR() {
		return MODULES_IMAGES_DIR;
	}

	/**
	 * @param mODULES_IMAGES_DIR
	 *            the mODULES_IMAGES_DIR to set
	 */
	public static void setMODULES_IMAGES_DIR(String mODULES_IMAGES_DIR) {
		MODULES_IMAGES_DIR = mODULES_IMAGES_DIR;
	}

	/**
	 * @return the uSER_PROFILE_IMAGES_DIR
	 */
	public static String getUSER_PROFILE_IMAGES_DIR() {
		return USER_PROFILE_IMAGES_DIR;
	}

	/**
	 * @param uSER_PROFILE_IMAGES_DIR
	 *            the uSER_PROFILE_IMAGES_DIR to set
	 */
	public static void setUSER_PROFILE_IMAGES_DIR(String uSER_PROFILE_IMAGES_DIR) {
		USER_PROFILE_IMAGES_DIR = uSER_PROFILE_IMAGES_DIR;
	}

	/**
	 * @return the uSER_PROFILE_NONE_IMAGE_DIR
	 */
	public static String getUSER_PROFILE_NONE_IMAGE_DIR() {
		return USER_PROFILE_NONE_IMAGE_DIR;
	}

	/**
	 * @param uSER_PROFILE_NONE_IMAGE_DIR
	 *            the uSER_PROFILE_NONE_IMAGE_DIR to set
	 */
	public static void setUSER_PROFILE_NONE_IMAGE_DIR(String uSER_PROFILE_NONE_IMAGE_DIR) {
		USER_PROFILE_NONE_IMAGE_DIR = uSER_PROFILE_NONE_IMAGE_DIR;
	}

	public static String getAppImgUserDir() {
		return APP_IMG_USER_DIR;
	}

	public static void setAppImgUserDir(String aPP_IMG_USER_DIR) {
		APP_IMG_USER_DIR = aPP_IMG_USER_DIR;
	}

	public static String getAPP_GUARDIAO_URL() {
		return APP_GUARDIAO_URL;
	}

	public static void setAPP_GUARDIAO_URL(String aPP_GUARDIAO_URL) {
		APP_GUARDIAO_URL = aPP_GUARDIAO_URL;
	}

	/**
	 * @return the tHIS_APP_DATABASE_SCHEMA
	 */
	public static String getTHIS_APP_DATABASE_SCHEMA() {
		return THIS_APP_DATABASE_SCHEMA;
	}

	/**
	 * @param tHIS_APP_DATABASE_SCHEMA the tHIS_APP_DATABASE_SCHEMA to set
	 */
	public static void setTHIS_APP_DATABASE_SCHEMA(String tHIS_APP_DATABASE_SCHEMA) {
		THIS_APP_DATABASE_SCHEMA = tHIS_APP_DATABASE_SCHEMA;
	}

	/**
	 * @return the pUBLIC_DATABASE_SCHEMA
	 */
	public static String getPUBLIC_DATABASE_SCHEMA() {
		return PUBLIC_DATABASE_SCHEMA;
	}

	/**
	 * @param pUBLIC_DATABASE_SCHEMA the pUBLIC_DATABASE_SCHEMA to set
	 */
	public static void setPUBLIC_DATABASE_SCHEMA(String pUBLIC_DATABASE_SCHEMA) {
		PUBLIC_DATABASE_SCHEMA = pUBLIC_DATABASE_SCHEMA;
	}

	/**
	 * @return the tEMP_PDF_SOLICITACAO
	 */
	public static String getTEMP_PDF_SOLICITACAO() {
		return TEMP_PDF_SOLICITACAO;
	}

	/**
	 * @param tEMP_PDF_SOLICITACAO the tEMP_PDF_SOLICITACAO to set
	 */
	public static void setTEMP_PDF_SOLICITACAO(String tEMP_PDF_SOLICITACAO) {
		TEMP_PDF_SOLICITACAO = tEMP_PDF_SOLICITACAO;
	}

	/**
	 * @return the lOGO_UFC
	 */
	public static String getLOGO_UFC() {
		return LOGO_UFC;
	}

	/**
	 * @param lOGO_UFC the lOGO_UFC to set
	 */
	public static void setLOGO_UFC(String lOGO_UFC) {
		LOGO_UFC = lOGO_UFC;
	}
	
}
