package br.gov.al.detran.detranfp;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.nitgen.SDK.BSP.NBioBSPJNI;

@Service
public class NBioCapture {

	private NBioBSPJNI bsp;
	private NBioBSPJNI.DEVICE_ENUM_INFO deviceEnumInfo;
	private String deviceInfo = "Auto_Detect";
	private NBioBSPJNI.Export exportEngine;

	/*
	 * private NBioBSPJNI.FIR_HANDLE hSavedFIR; private NBioBSPJNI.FIR_HANDLE
	 * hSavedAuditFIR; private NBioBSPJNI.FIR fullSavedFIR; private
	 * NBioBSPJNI.FIR_TEXTENCODE textSavedFIR;
	 */

	/* *//**
			 * Starts up and configures the BIRT Report Engine
			 *//*
			 * @PostConstruct public void startUp() throws NBioBSPJNIException {
			 * 
			 * bsp = getBsp();
			 * 
			 * if (checkError()) { throw new
			 * NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()
			 * )); }
			 * 
			 * }
			 */

	public NBioBSPJNI getBsp() throws NBioBSPJNIException {

		if (bsp == null) {
			bsp = new NBioBSPJNI();

			if (checkError()) {
				int coderet = bsp.GetErrorCode();
				terminate();
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(coderet));
			}

			SetDeviceList();
			exportEngine = bsp.new Export();

			if (checkError()) {
				int coderet = bsp.GetErrorCode();
				terminate();
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(coderet));
			}

			bsp.OpenDevice(deviceEnumInfo.DeviceInfo[0].NameID, deviceEnumInfo.DeviceInfo[0].Instance);

			if (checkError()) {
				int coderet = bsp.GetErrorCode();
				terminate();
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(coderet));
			}
		}

		return bsp;
	}

	private Boolean checkError() {
		if (bsp.IsErrorOccured()) {

			return true;
		}

		return false;
	}

	private boolean SetDeviceList() throws NBioBSPJNIException {
		int i, n;

		boolean result = true;

		deviceEnumInfo = bsp.new DEVICE_ENUM_INFO();
		bsp.EnumerateDevice(deviceEnumInfo);

		if (checkError()) {
			int bspCode = bsp.GetErrorCode();
			throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bspCode));
		}

		n = deviceEnumInfo.DeviceCount;

		if (n == 0) {
			int bspCode = 100; // bsp.GetErrorCode();
			result = false;
			terminate();
			throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bspCode));

		}

		this.deviceInfo = "Auto_Detect";

		for (i = 0; i < n; i++) {
			this.deviceInfo = deviceEnumInfo.DeviceInfo[i].Name + "(ID: " + deviceEnumInfo.DeviceInfo[i].Instance + ") "
					+ deviceEnumInfo.DeviceInfo[i].Description;
		}

		return result;
	}

	public String getDeviceInfo() {
		return this.deviceInfo;
	}

	/*
	 * Capture digital
	 *
	 *
	 */

	public String captureIMG() throws NBioBSPJNIException {

		String strb64 = "";
		NBioBSPJNI.FIR_HANDLE hSavedFIR = null;
		NBioBSPJNI.FIR_HANDLE hSavedAuditFIR = null;

		try {
			bsp = getBsp();

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			hSavedAuditFIR = bsp.new FIR_HANDLE();

			hSavedFIR = bsp.new FIR_HANDLE();

			NBioBSPJNI.WINDOW_OPTION WOP = bsp.new WINDOW_OPTION();
			WOP.WindowStyle = NBioBSPJNI.WINDOW_STYLE.INVISIBLE;

			bsp.Capture(2, hSavedFIR, 5000, hSavedAuditFIR, WOP);

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			if (hSavedAuditFIR != null) {
				NBioBSPJNI.INPUT_FIR inputFIR = bsp.new INPUT_FIR();

				inputFIR.SetFIRHandle(hSavedAuditFIR);

				NBioBSPJNI.Export.AUDIT exportAudit = exportEngine.new AUDIT();

				exportEngine.ExportAudit(inputFIR, exportAudit);

				if (checkError()) {
					throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
				}

				java.awt.image.BufferedImage bufImage = new java.awt.image.BufferedImage(exportAudit.ImageWidth,
						exportAudit.ImageHeight, java.awt.image.BufferedImage.TYPE_BYTE_GRAY);
				bufImage.getRaster().setDataElements(0, 0, exportAudit.ImageWidth, exportAudit.ImageHeight,
						exportAudit.FingerData[0].Template[0].Data);

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				javax.imageio.ImageIO.write(bufImage, "jpg", baos);
				baos.flush();

				strb64 = Base64.getEncoder().encodeToString(baos.toByteArray());

			}

			return strb64;

		} catch (java.io.IOException ex) {
			throw new NBioBSPJNIException("Jpeg File IOException!!");

		} finally {

			if (hSavedFIR != null) {
				hSavedFIR.dispose();
				hSavedFIR = null;
			}

			if (hSavedAuditFIR != null) {
				hSavedAuditFIR.dispose();
				hSavedAuditFIR = null;
			}

			terminate();

		}

	}

	public String capturar() throws NBioBSPJNIException {

		NBioBSPJNI.FIR_HANDLE hSavedFIR = null;
		NBioBSPJNI.FIR fullSavedFIR = null;
		NBioBSPJNI.FIR_HANDLE hSavedAuditFIR = null;
		NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = null;

		try {

			bsp = getBsp();

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			hSavedFIR = bsp.new FIR_HANDLE();

			NBioBSPJNI.WINDOW_OPTION WOP = bsp.new WINDOW_OPTION();
			WOP.WindowStyle = NBioBSPJNI.WINDOW_STYLE.INVISIBLE;

			bsp.Capture(2, hSavedFIR, 5000, hSavedAuditFIR, WOP);

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			fullSavedFIR = bsp.new FIR();
			bsp.GetFIRFromHandle(hSavedFIR, fullSavedFIR);

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			textSavedFIR = bsp.new FIR_TEXTENCODE();

			bsp.GetTextFIRFromHandle(hSavedFIR, textSavedFIR);

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			return textSavedFIR.TextFIR;

		} finally {

			if (hSavedFIR != null) {
				hSavedFIR.dispose();
				hSavedFIR = null;
			}

			if (fullSavedFIR != null) {
				fullSavedFIR = null;
			}
			if (textSavedFIR != null) {
				textSavedFIR = null;
			}

			terminate();
		}

	}

	////////////////////////////////////////////////////////////////////////////////

	public String capturarFP() throws NBioBSPJNIException {

		NBioBSPJNI.FIR_HANDLE hSavedFIR = null;
		NBioBSPJNI.FIR fullSavedFIR = null;
		NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = null;
		NBioBSPJNI.FIR_HANDLE hSavedAuditFIR = null;

		bsp = getBsp();

		if (checkError()) {
			throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
		}

		hSavedFIR = bsp.new FIR_HANDLE();

		NBioBSPJNI.WINDOW_OPTION WOP = bsp.new WINDOW_OPTION();
		WOP.WindowStyle = NBioBSPJNI.WINDOW_STYLE.INVISIBLE;

		bsp.Capture(2, hSavedFIR, 5000, hSavedAuditFIR, WOP);

		if (checkError()) {
			throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
		}

		fullSavedFIR = bsp.new FIR();
		bsp.GetFIRFromHandle(hSavedFIR, fullSavedFIR);

		if (checkError())
			throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));

		textSavedFIR = bsp.new FIR_TEXTENCODE();
		bsp.GetTextFIRFromHandle(hSavedFIR, textSavedFIR);
		if (checkError()) {
			throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
		}

		return textSavedFIR.TextFIR;

	}

	///////////////////////////////////////////////////////////////////////////////

	public boolean verificar(String digitalInformada) throws NBioBSPJNIException {

		Boolean bResult = new Boolean(false);
		NBioBSPJNI.FIR_TEXTENCODE textSavedFIR1 = null;
		NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = null;
		NBioBSPJNI.FIR_PAYLOAD payload = null;
		NBioBSPJNI.INPUT_FIR inputFIR = null;
		NBioBSPJNI.INPUT_FIR inputFIR1 = null;
		try {

			bsp = getBsp();

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			// Make two obj for two files.
			inputFIR = bsp.new INPUT_FIR();
			inputFIR1 = bsp.new INPUT_FIR();

			payload = bsp.new FIR_PAYLOAD();

			textSavedFIR = bsp.new FIR_TEXTENCODE();
			textSavedFIR.TextFIR = capturarFP();

			textSavedFIR1 = bsp.new FIR_TEXTENCODE();
			textSavedFIR1.TextFIR = digitalInformada;

			// Set both files in these objects.
			inputFIR.SetTextFIR(textSavedFIR);
			inputFIR1.SetTextFIR(textSavedFIR1);

			// call verify mathod.
			bsp.VerifyMatch(inputFIR1, inputFIR, bResult, payload);

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

		} finally {

			if (textSavedFIR1 != null) {
				textSavedFIR1 = null;
			}

			if (textSavedFIR != null) {
				textSavedFIR = null;
			}

			if (payload != null) {
				payload = null;
			}

			if (inputFIR != null) {
				inputFIR = null;
			}

			if (inputFIR1 != null) {
				inputFIR1 = null;
			}

			terminate();
		}

		return bResult;
	}

	/**
	 *
	 * Cadastrar biometria
	 * 
	 * @throws NBioBSPJNIException
	 *
	 */

	public String criar() throws NBioBSPJNIException {

		NBioBSPJNI.FIR_HANDLE hSavedFIR = null;
		NBioBSPJNI.FIR fullSavedFIR = null;
		NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = null;

		try {

			bsp = getBsp();

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			NBioBSPJNI.FIR_PAYLOAD payLoad;
			String szValue = "";

			payLoad = bsp.new FIR_PAYLOAD();

			hSavedFIR = bsp.new FIR_HANDLE();

			bsp.Enroll(null, hSavedFIR, (szValue.length() > 0) ? payLoad : null, -1, null, null);

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			fullSavedFIR = bsp.new FIR();
			bsp.GetFIRFromHandle(hSavedFIR, fullSavedFIR);

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			textSavedFIR = bsp.new FIR_TEXTENCODE();
			bsp.GetTextFIRFromHandle(hSavedFIR, textSavedFIR);

			if (checkError()) {
				throw new NBioBSPJNIException(NBioExceptionCodes.valueOf(bsp.GetErrorCode()));
			}

			return textSavedFIR.TextFIR;

		} finally {

			if (hSavedFIR != null) {
				hSavedFIR.dispose();
				hSavedFIR = null;
			}

			if (fullSavedFIR != null) {
				fullSavedFIR = null;
			}

			if (textSavedFIR != null) {
				textSavedFIR = null;
			}

			terminate();
		}

	}

	public String criar2() throws NBioBSPJNIException {

		NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = null;

		try {

			NBioBSPJNI.FIR_HANDLE hSavedFIR;
			hSavedFIR = bsp.new FIR_HANDLE();

			bsp.Enroll(hSavedFIR, null);
			bsp.Capture(hSavedFIR);

			// Get Text FIR
			if (bsp.IsErrorOccured() == false) {
				textSavedFIR = bsp.new FIR_TEXTENCODE();
				bsp.GetTextFIRFromHandle(hSavedFIR, textSavedFIR);
			}

			// Get Binary FIR

			if (bsp.IsErrorOccured() == false) {
				NBioBSPJNI.FIR fullSavedFIR;

				fullSavedFIR = bsp.new FIR();
				bsp.GetFIRFromHandle(hSavedFIR, fullSavedFIR);

			}

			return textSavedFIR.TextFIR;

		} finally {

			if (textSavedFIR != null) {
				textSavedFIR = null;
			}

			terminate();

		}

	}

	/**
	 *
	 * Limpar memoria
	 *
	 */

	public void terminate() {
		if (bsp != null) {
			short deviceID = bsp.GetOpenedDeviceID();
			bsp.CloseDevice(deviceID);
			bsp.dispose();
			bsp = null;
		}

	}

}
