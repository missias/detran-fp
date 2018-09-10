package br.gov.al.detran.detranfp;

import org.springframework.http.HttpStatus;

public enum NBioExceptionCodes {
	INVALID_HANDLE(1, "Solicitação inválida", HttpStatus.FORBIDDEN),
	INVALID_POINTER(2, "Solicitação inválida",	HttpStatus.FORBIDDEN),
	INVALID_TYPE(3, "Solicitação inválida",	HttpStatus.FORBIDDEN),
	FUNCTION_FAILED(4, "Falha na execução da função", HttpStatus.INTERNAL_SERVER_ERROR),
	STRUCT_FAILED(5, "Estrutura do input não suportada", HttpStatus.INTERNAL_SERVER_ERROR),
	INVALID_INPUT(11, "Input inválida", HttpStatus.INTERNAL_SERVER_ERROR),


	UNKNOWN_FORMAT(13,       "0x0d Unknown FIR data format",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x0d Unknown FIR data format
	UNKNOWN_VERSION(14,      "0x0e Unknown BSP version" ,HttpStatus.INTERNAL_SERVER_ERROR),     // 0x0e Unknown BSP version
	VALIDITY_FAIL(15,        "0x0f BSP validity fail",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x0f BSP validity fail
	INIT_MAXFINGER(16,       "0x10 BSP Maxfinger option set fail",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x10 BSP Maxfinger option set fail
	INIT_SAMPLESPERFINGER(17,"0x11 BSP Samplesperfinger option set fail",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x11 BSP Samplesperfinger option set fail
	INIT_ENROLLQUALITY(18,   "0x12 BSP EnrollQuality option set fail",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x12 BSP EnrollQuality option set fail
	INIT_VERIFYQUALITY(19,   "0x13 BSP VerifyQuality option set fail",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x13 BSP VerifyQuality option set fail
	INIT_IDENTIFYQUALITY(20, "0x14 BSP IdentifyQuality option set fail",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x14 BSP IdentifyQuality option set fail
	INIT_SECURITYLEVEL(21,   "0x15 BSP SecurityLevel option set fail",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x15 BSP SecurityLevel option set fail
	INVALID_MINSIZE(22,      "0x16 Template data size error",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x16 Template data size error
	INVALID_TEMPLATE(23,     "0x17 Invalid template data",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x17 Invalid template data
	EXPIRED_VERSION(24,      "0x18 Expired BSP",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x18 Expired BSP
	OUT_OF_MEMORY(36,        "0x24 Out of memory",HttpStatus.INTERNAL_SERVER_ERROR),      // 0x18 Expired BSP
	DEVICE_OPEN_FAIL         (257,       "0x101 Device open fail",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x101 Device open fail
	INVALID_DEVICE_ID        (258,       "0x102 Invalid device ID",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x102 Invalid device ID
	WRONG_DEVICE_ID          (259,       "0x103 Wrong device ID",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x103 Wrong device ID
	DEVICE_ALREADY_OPENED    (260,       "0x104 Already open device",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x104 Already open device
	DEVICE_NOT_OPENED        (261,       "0x105 Device not opened",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x105 Device not opened
	DEVICE_BRIGHTNESS        (262,       "0x106 Invalid Brightness option value",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x106 Invalid Brightness option value
	DEVICE_CONTRAST          (263,       "0x107 Invalid Contrast option value",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x107 Invalid Contrast option value
	DEVICE_GAIN              (264,       "0x108 Invalid Gain option value",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x108 Invalid Gain option value
	LOWVERSION_DRIVER        (265,       "0x109 Low version driver",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x109 Low version driver
	DEVICE_INIT_FAIL         (266,       "0x10a Device initialize fail",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x10a Device initialize fail
	DEVICE_LOST_DEVICE       (267,       "0x10b Device disconnected.",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x10b Device disconnected.
	DEVICE_DLL_LOAD_FAIL     (268,       "0x10c Device module load fail.",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x10c Device module load fail.
	DEVICE_MAKE_INSTANCE_FAIL(269,       "0x10d Device Instance creation fail.",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x10d Device Instance creation fail.
	DEVICE_DLL_GET_PROC_FAIL (270,       "0x10e Device function load fail.",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x10e Device function load fail.
	DEVICE_IO_CONTROL_FAIL   (271,       "0x10f Device IO fail.",HttpStatus.INTERNAL_SERVER_ERROR),  // 0x10f Device IO fail.

	USER_CANCEL (513,       "0x201 Operation cancel from user ",HttpStatus.INTERNAL_SERVER_ERROR), //0x201 Operation cancel from user
	USER_BACK   (514,       "0x202 Operation back from user   ",HttpStatus.INTERNAL_SERVER_ERROR), //0x202 Operation back from user

	CAPTURE_FAKE_SUSPICIOUS  (516,"Tempo limite de execução atingida",HttpStatus.INTERNAL_SERVER_ERROR),      //0x204 Fake input occurred
	ENROLL_EVENT_PLACE       (517,"0x205 Enroll method event",HttpStatus.INTERNAL_SERVER_ERROR),      //0x205 Enroll method event
	ENROLL_EVENT_HOLD        (518,"0x206 Enroll method event",HttpStatus.INTERNAL_SERVER_ERROR),      //0x206 Enroll method event
	ENROLL_EVENT_REMOVE      (519,"0x207 Enroll method event",HttpStatus.INTERNAL_SERVER_ERROR),      //0x207 Enroll method event
	ENROLL_EVENT_PLACE_AGAIN (520,"0x208 Enroll method event",HttpStatus.INTERNAL_SERVER_ERROR),      //0x208 Enroll method event
	ENROLL_EVENT_EXTRACT     (521,"0x209 Enroll method event",HttpStatus.INTERNAL_SERVER_ERROR),      //0x209 Enroll method event
	ENROLL_EVENT_MATCH_FAILED(522,"0x20a Enroll method event",HttpStatus.INTERNAL_SERVER_ERROR),      //0x20a Enroll method event
	INIT_PRESEARCHRATE       (1281,"0x501 Invalid PreSearchRate option value",HttpStatus.INTERNAL_SERVER_ERROR),      //0x501 Invalid PreSearchRate option value
	INDEXSEARCH_INIT_FAIL    (1282,"0x502 IndexSearch engine initialize failed",HttpStatus.INTERNAL_SERVER_ERROR),      //0x502 IndexSearch engine initialize failed
	INDEXSEARCH_SAVE_DB      (1283,"0x503 IndexSearch engine save db failed",HttpStatus.INTERNAL_SERVER_ERROR),      //0x503 IndexSearch engine save db failed
	INDEXSEARCH_LOAD_DB      (1284,"0x504 IndexSearch engine load db failed",HttpStatus.INTERNAL_SERVER_ERROR),      //0x504 IndexSearch engine load db failed
	INDEXSEARCH_UNKNOWN_VER  (1285,"0x505 Unknown IndexSearch engine version",HttpStatus.INTERNAL_SERVER_ERROR),      //0x505 Unknown IndexSearch engine version
	INDEXSEARCH_IDENTIFY_FAIL(1286,"0x506 IndexSearch engine identify failed",HttpStatus.INTERNAL_SERVER_ERROR),      //0x506 IndexSearch engine identify failed
	INDEXSEARCH_DUPLICATED_ID(1287,"0x507 IndexSearch engine ID duplicated",HttpStatus.INTERNAL_SERVER_ERROR),      //0x507 IndexSearch engine ID duplicated
	INDEXSEARCH_IDENTIFY_STOP(1288,"0x508 IndexSearch engine identify stop from user",HttpStatus.INTERNAL_SERVER_ERROR),      //0x508 IndexSearch engine identify stop from user

	CAPTURE_TIMEOUT(515, "Tempo limite de execução atingida", HttpStatus.REQUEST_TIMEOUT),

	NO_DEVICE(100, "Nenhum leitor encontrado", HttpStatus.FORBIDDEN);

	private final int id;
	private final String msg;
	private final HttpStatus status;

	NBioExceptionCodes(int id, String msg, HttpStatus status) {
		this.id = id;
		this.msg = msg;
		this.status = status;
	}

	public int getId() {
		return this.id;
	}

	public String getMsg() {
		return this.msg;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public static NBioExceptionCodes valueOf(int statusCode) {
		for (NBioExceptionCodes status : values()) {
			if (status.id == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}

}