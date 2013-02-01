package com.thingtrack.konekti.domain.mobile.device;

import org.simpleframework.xml.Element;

public class TelemetryCaptureDevice extends CaptureDevice {

	public static final String TOPIC_BASE = "com/thingtrack/konekti/sensor/telemetry/mobile";
	
	@Element
	private int identifier;
	@Element
	private String identifieId;
	@Element
	private String Time1;
	@Element
	private String EngSpeed;
	@Element
	private String Accel;
	@Element
	private String TCO_Speed;
	@Element
	private String TCO_MD;
	@Element
	private String TCO_OS;
	@Element
	private String TCO_DI;
	@Element
	private String TCO_DP;
	@Element
	private String TCO_HI;
	@Element
	private String TCO_EV;
	@Element
	private String TCO_D1_PR;
	@Element
	private String TCO_D1_WS;
	@Element
	private String TCO_D1_TS;
	@Element
	private String TCO_D2_PR;
	@Element
	private String TCO_D2_WS;
	@Element
	private String TCO_D2_TS;
	@Element
	private String VehSpeed;
	@Element
	private String CC;
	@Element
	private String BR;
	@Element
	private String CS;
	@Element
	private String PB;
	@Element
	private String Distance;
	@Element
	private String EngHours;
	@Element
	private String FuelC;
	@Element
	private String EngTemp;
	@Element
	private String FuelLev;
	@Element
	private String VehID;
	@Element
	private String FMS_Versd;
	@Element
	private String FMS_Diag;
	@Element
	private String FMS_Requ;
	@Element
	private String Gear_S;
	@Element
	private String Gear_C;
	@Element
	private String DC1_P;
	@Element
	private String DC1_R;
	@Element
	private String DC1_S;
	@Element
	private String DC2_1;
	@Element
	private String DC2_2;
	@Element
	private String DC2_3;
	@Element
	private String DC2_4;
	@Element
	private String DC2_5;
	@Element
	private String DC2_6;
	@Element
	private String DC2_7;
	@Element
	private String DC2_8;
	@Element
	private String DC2_9;
	@Element
	private String DC2_10;	
	@Element
	private String BellowPr_FAL;
	@Element
	private String BellowPr_FAR;
	@Element
	private String BellowPr_RAL;
	@Element
	private String BellowPr_RAR;
	@Element
	private String BrakePR_1;
	@Element
	private String BrakePr_2;
	@Element
	private String Altern_1;
	@Element
	private String Altern_2;
	@Element
	private String Altern_3;
	@Element
	private String Altern_4;
	@Element
	private String DateTime;
	@Element
	private String AmbTemp;
	@Element
	private String Card1_ID;
	@Element
	private String Card1_Type;
	@Element
	private String Card1_Country;
	@Element
	private String Card2_ID;
	@Element
	private String Card2_Type;
	@Element
	private String Card2_Country;
	@Element
	private String FuelEco_l_h;
	@Element
	private String FuelEco_km_l;
	@Element
	private String TS_1;
	@Element
	private String TS_2;
	@Element
	private String TS_3;
	@Element
	private String TS_4;
	@Element
	private String TS_5;
	@Element
	private String TS_6;
	@Element
	private String TS_7;
	@Element
	private String TS_8;
	@Element
	private String TS_9;
	@Element
	private String TS_10;
	@Element
	private String TS_11;
	@Element
	private String TS_12;
	@Element
	private String TS_13;
	@Element
	private String TS_14;
	@Element
	private String TS_15;
	@Element
	private String TS_16;
	@Element
	private String TS_17;
	@Element
	private String TS_18;
	@Element
	private String TS_19;
	@Element
	private String TS_20;
	@Element
	private String TS_21;
	@Element
	private String TS_22;
	@Element
	private String TS_23;
	@Element
	private String TS_24;
	@Element
	private String TS_25;
	@Element
	private String TS_26;
	@Element
	private String TS_27;	
	@Element
	private String TS_28;
	@Element
	private String TS_29;
	@Element
	private String TS_30;
	@Element
	private String TS_31;
	@Element
	private String TS_32;
	@Element
	private String TS_33;
	@Element
	private String TS_34;
	@Element
	private String TS_35;
	@Element
	private String TS_36;
	@Element
	private String TS_37;
	@Element
	private String TS_38;
	@Element
	private String TS_39;
	@Element
	private String TS_40;
	@Element
	private String TS_41;

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	public int getIdentifier() {
		return identifier;
	}
	public void setIdentifieId(String identifieId) {
		this.identifieId = identifieId;
	}
	public String getIdentifieId() {
		return identifieId;
	}
	public String getTime1() {
		return Time1;
	}
	public void setTime1(String time1) {
		Time1 = time1;
	}
	public String getEngSpeed() {
		return EngSpeed;
	}
	public void setEngSpeed(String engSpeed) {
		EngSpeed = engSpeed;
	}
	public String getAccel() {
		return Accel;
	}
	public void setAccel(String accel) {
		Accel = accel;
	}
	public String getTCO_Speed() {
		return TCO_Speed;
	}
	public void setTCO_Speed(String tCO_Speed) {
		TCO_Speed = tCO_Speed;
	}
	public String getTCO_MD() {
		return TCO_MD;
	}
	public void setTCO_MD(String tCO_MD) {
		TCO_MD = tCO_MD;
	}
	public String getTCO_OS() {
		return TCO_OS;
	}
	public void setTCO_OS(String tCO_OS) {
		TCO_OS = tCO_OS;
	}
	public String getTCO_DI() {
		return TCO_DI;
	}
	public void setTCO_DI(String tCO_DI) {
		TCO_DI = tCO_DI;
	}
	public String getTCO_DP() {
		return TCO_DP;
	}
	public void setTCO_DP(String tCO_DP) {
		TCO_DP = tCO_DP;
	}
	public String getTCO_HI() {
		return TCO_HI;
	}
	public void setTCO_HI(String tCO_HI) {
		TCO_HI = tCO_HI;
	}
	public String getTCO_EV() {
		return TCO_EV;
	}
	public void setTCO_EV(String tCO_EV) {
		TCO_EV = tCO_EV;
	}
	public String getTCO_D1_PR() {
		return TCO_D1_PR;
	}
	public void setTCO_D1_PR(String tCO_D1_PR) {
		TCO_D1_PR = tCO_D1_PR;
	}
	public String getTCO_D1_WS() {
		return TCO_D1_WS;
	}
	public void setTCO_D1_WS(String tCO_D1_WS) {
		TCO_D1_WS = tCO_D1_WS;
	}
	public String getTCO_D1_TS() {
		return TCO_D1_TS;
	}
	public void setTCO_D1_TS(String tCO_D1_TS) {
		TCO_D1_TS = tCO_D1_TS;
	}
	public String getTCO_D2_PR() {
		return TCO_D2_PR;
	}
	public void setTCO_D2_PR(String tCO_D2_PR) {
		TCO_D2_PR = tCO_D2_PR;
	}
	public String getTCO_D2_WS() {
		return TCO_D2_WS;
	}
	public void setTCO_D2_WS(String tCO_D2_WS) {
		TCO_D2_WS = tCO_D2_WS;
	}
	public String getTCO_D2_TS() {
		return TCO_D2_TS;
	}
	public void setTCO_D2_TS(String tCO_D2_TS) {
		TCO_D2_TS = tCO_D2_TS;
	}
	public String getVehSpeed() {
		return VehSpeed;
	}
	public void setVehSpeed(String vehSpeed) {
		VehSpeed = vehSpeed;
	}
	public String getCC() {
		return CC;
	}
	public void setCC(String cC) {
		CC = cC;
	}
	public String getBR() {
		return BR;
	}
	public void setBR(String bR) {
		BR = bR;
	}
	public String getCS() {
		return CS;
	}
	public void setCS(String cS) {
		CS = cS;
	}
	public String getPB() {
		return PB;
	}
	public void setPB(String pB) {
		PB = pB;
	}
	public String getDistance() {
		return Distance;
	}
	public void setDistance(String distance) {
		Distance = distance;
	}
	public String getEngHours() {
		return EngHours;
	}
	public void setEngHours(String engHours) {
		EngHours = engHours;
	}
	public String getFuelC() {
		return FuelC;
	}
	public void setFuelC(String fuelC) {
		FuelC = fuelC;
	}
	public String getEngTemp() {
		return EngTemp;
	}
	public void setEngTemp(String engTemp) {
		EngTemp = engTemp;
	}
	public String getFuelLev() {
		return FuelLev;
	}
	public void setFuelLev(String fuelLev) {
		FuelLev = fuelLev;
	}
	public String getVehID() {
		return VehID;
	}
	public void setVehID(String vehID) {
		VehID = vehID;
	}
	public String getFMS_Versd() {
		return FMS_Versd;
	}
	public void setFMS_Versd(String fMS_Versd) {
		FMS_Versd = fMS_Versd;
	}
	public String getFMS_Diag() {
		return FMS_Diag;
	}
	public void setFMS_Diag(String fMS_Diag) {
		FMS_Diag = fMS_Diag;
	}
	public String getFMS_Requ() {
		return FMS_Requ;
	}
	public void setFMS_Requ(String fMS_Requ) {
		FMS_Requ = fMS_Requ;
	}
	public String getGear_S() {
		return Gear_S;
	}
	public void setGear_S(String gear_S) {
		Gear_S = gear_S;
	}
	public String getGear_C() {
		return Gear_C;
	}
	public void setGear_C(String gear_C) {
		Gear_C = gear_C;
	}
	public String getDC1_P() {
		return DC1_P;
	}
	public void setDC1_P(String dC1_P) {
		DC1_P = dC1_P;
	}
	public String getDC1_R() {
		return DC1_R;
	}
	public void setDC1_R(String dC1_R) {
		DC1_R = dC1_R;
	}
	public String getDC1_S() {
		return DC1_S;
	}
	public void setDC1_S(String dC1_S) {
		DC1_S = dC1_S;
	}
	public String getDC2_1() {
		return DC2_1;
	}
	public void setDC2_1(String dC2_1) {
		DC2_1 = dC2_1;
	}
	public String getDC2_2() {
		return DC2_2;
	}
	public void setDC2_2(String dC2_2) {
		DC2_2 = dC2_2;
	}
	public String getDC2_3() {
		return DC2_3;
	}
	public void setDC2_3(String dC2_3) {
		DC2_3 = dC2_3;
	}
	public String getDC2_4() {
		return DC2_4;
	}
	public void setDC2_4(String dC2_4) {
		DC2_4 = dC2_4;
	}
	public String getDC2_5() {
		return DC2_5;
	}
	public void setDC2_5(String dC2_5) {
		DC2_5 = dC2_5;
	}
	public String getDC2_6() {
		return DC2_6;
	}
	public void setDC2_6(String dC2_6) {
		DC2_6 = dC2_6;
	}
	public String getDC2_7() {
		return DC2_7;
	}
	public void setDC2_7(String dC2_7) {
		DC2_7 = dC2_7;
	}
	public String getDC2_8() {
		return DC2_8;
	}
	public void setDC2_8(String dC2_8) {
		DC2_8 = dC2_8;
	}
	public String getDC2_9() {
		return DC2_9;
	}
	public void setDC2_9(String dC2_9) {
		DC2_9 = dC2_9;
	}
	public String getDC2_10() {
		return DC2_10;
	}
	public void setDC2_10(String dC2_10) {
		DC2_10 = dC2_10;
	}
	public String getBellowPr_FAL() {
		return BellowPr_FAL;
	}
	public void setBellowPr_FAL(String bellowPr_FAL) {
		BellowPr_FAL = bellowPr_FAL;
	}
	public String getBellowPr_FAR() {
		return BellowPr_FAR;
	}
	public void setBellowPr_FAR(String bellowPr_FAR) {
		BellowPr_FAR = bellowPr_FAR;
	}
	public String getBellowPr_RAL() {
		return BellowPr_RAL;
	}
	public void setBellowPr_RAL(String bellowPr_RAL) {
		BellowPr_RAL = bellowPr_RAL;
	}
	public String getBellowPr_RAR() {
		return BellowPr_RAR;
	}
	public void setBellowPr_RAR(String bellowPr_RAR) {
		BellowPr_RAR = bellowPr_RAR;
	}
	public String getBrakePR_1() {
		return BrakePR_1;
	}
	public void setBrakePR_1(String brakePR_1) {
		BrakePR_1 = brakePR_1;
	}
	public String getBrakePr_2() {
		return BrakePr_2;
	}
	public void setBrakePr_2(String brakePr_2) {
		BrakePr_2 = brakePr_2;
	}
	public String getAltern_1() {
		return Altern_1;
	}
	public void setAltern_1(String altern_1) {
		Altern_1 = altern_1;
	}
	public String getAltern_2() {
		return Altern_2;
	}
	public void setAltern_2(String altern_2) {
		Altern_2 = altern_2;
	}
	public String getAltern_3() {
		return Altern_3;
	}
	public void setAltern_3(String altern_3) {
		Altern_3 = altern_3;
	}
	public String getAltern_4() {
		return Altern_4;
	}
	public void setAltern_4(String altern_4) {
		Altern_4 = altern_4;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	public String getAmbTemp() {
		return AmbTemp;
	}
	public void setAmbTemp(String ambTemp) {
		AmbTemp = ambTemp;
	}
	public String getCard1_ID() {
		return Card1_ID;
	}
	public void setCard1_ID(String card1_ID) {
		Card1_ID = card1_ID;
	}
	public String getCard1_Type() {
		return Card1_Type;
	}
	public void setCard1_Type(String card1_Type) {
		Card1_Type = card1_Type;
	}
	public String getCard1_Country() {
		return Card1_Country;
	}
	public void setCard1_Country(String card1_Country) {
		Card1_Country = card1_Country;
	}
	public String getCard2_ID() {
		return Card2_ID;
	}
	public void setCard2_ID(String card2_ID) {
		Card2_ID = card2_ID;
	}
	public String getCard2_Type() {
		return Card2_Type;
	}
	public void setCard2_Type(String card2_Type) {
		Card2_Type = card2_Type;
	}
	public String getCard2_Country() {
		return Card2_Country;
	}
	public void setCard2_Country(String card2_Country) {
		Card2_Country = card2_Country;
	}
	public String getFuelEco_l_h() {
		return FuelEco_l_h;
	}
	public void setFuelEco_l_h(String fuelEco_l_h) {
		FuelEco_l_h = fuelEco_l_h;
	}
	public String getFuelEco_km_l() {
		return FuelEco_km_l;
	}
	public void setFuelEco_km_l(String fuelEco_km_l) {
		FuelEco_km_l = fuelEco_km_l;
	}
	public String getTS_1() {
		return TS_1;
	}
	public void setTS_1(String tS_1) {
		TS_1 = tS_1;
	}
	public String getTS_2() {
		return TS_2;
	}
	public void setTS_2(String tS_2) {
		TS_2 = tS_2;
	}
	public String getTS_3() {
		return TS_3;
	}
	public void setTS_3(String tS_3) {
		TS_3 = tS_3;
	}
	public String getTS_4() {
		return TS_4;
	}
	public void setTS_4(String tS_4) {
		TS_4 = tS_4;
	}
	public String getTS_5() {
		return TS_5;
	}
	public void setTS_5(String tS_5) {
		TS_5 = tS_5;
	}
	public String getTS_6() {
		return TS_6;
	}
	public void setTS_6(String tS_6) {
		TS_6 = tS_6;
	}
	public String getTS_7() {
		return TS_7;
	}
	public void setTS_7(String tS_7) {
		TS_7 = tS_7;
	}
	public String getTS_8() {
		return TS_8;
	}
	public void setTS_8(String tS_8) {
		TS_8 = tS_8;
	}
	public String getTS_9() {
		return TS_9;
	}
	public void setTS_9(String tS_9) {
		TS_9 = tS_9;
	}
	public String getTS_10() {
		return TS_10;
	}
	public void setTS_10(String tS_10) {
		TS_10 = tS_10;
	}
	public String getTS_11() {
		return TS_11;
	}
	public void setTS_11(String tS_11) {
		TS_11 = tS_11;
	}
	public String getTS_12() {
		return TS_12;
	}
	public void setTS_12(String tS_12) {
		TS_12 = tS_12;
	}
	public String getTS_13() {
		return TS_13;
	}
	public void setTS_13(String tS_13) {
		TS_13 = tS_13;
	}
	public String getTS_14() {
		return TS_14;
	}
	public void setTS_14(String tS_14) {
		TS_14 = tS_14;
	}
	public String getTS_15() {
		return TS_15;
	}
	public void setTS_15(String tS_15) {
		TS_15 = tS_15;
	}
	public String getTS_16() {
		return TS_16;
	}
	public void setTS_16(String tS_16) {
		TS_16 = tS_16;
	}
	public String getTS_17() {
		return TS_17;
	}
	public void setTS_17(String tS_17) {
		TS_17 = tS_17;
	}
	public String getTS_18() {
		return TS_18;
	}
	public void setTS_18(String tS_18) {
		TS_18 = tS_18;
	}
	public String getTS_19() {
		return TS_19;
	}
	public void setTS_19(String tS_19) {
		TS_19 = tS_19;
	}
	public String getTS_20() {
		return TS_20;
	}
	public void setTS_20(String tS_20) {
		TS_20 = tS_20;
	}
	public String getTS_21() {
		return TS_21;
	}
	public void setTS_21(String tS_21) {
		TS_21 = tS_21;
	}
	public String getTS_22() {
		return TS_22;
	}
	public void setTS_22(String tS_22) {
		TS_22 = tS_22;
	}
	public String getTS_23() {
		return TS_23;
	}
	public void setTS_23(String tS_23) {
		TS_23 = tS_23;
	}
	public String getTS_24() {
		return TS_24;
	}
	public void setTS_24(String tS_24) {
		TS_24 = tS_24;
	}
	public String getTS_25() {
		return TS_25;
	}
	public void setTS_25(String tS_25) {
		TS_25 = tS_25;
	}
	public String getTS_26() {
		return TS_26;
	}
	public void setTS_26(String tS_26) {
		TS_26 = tS_26;
	}
	public String getTS_27() {
		return TS_27;
	}
	public void setTS_27(String tS_27) {
		TS_27 = tS_27;
	}
	public String getTS_28() {
		return TS_28;
	}
	public void setTS_28(String tS_28) {
		TS_28 = tS_28;
	}
	public String getTS_29() {
		return TS_29;
	}
	public void setTS_29(String tS_29) {
		TS_29 = tS_29;
	}
	public String getTS_30() {
		return TS_30;
	}
	public void setTS_30(String tS_30) {
		TS_30 = tS_30;
	}
	public String getTS_31() {
		return TS_31;
	}
	public void setTS_31(String tS_31) {
		TS_31 = tS_31;
	}
	public String getTS_32() {
		return TS_32;
	}
	public void setTS_32(String tS_32) {
		TS_32 = tS_32;
	}
	public String getTS_33() {
		return TS_33;
	}
	public void setTS_33(String tS_33) {
		TS_33 = tS_33;
	}
	public String getTS_34() {
		return TS_34;
	}
	public void setTS_34(String tS_34) {
		TS_34 = tS_34;
	}
	public String getTS_35() {
		return TS_35;
	}
	public void setTS_35(String tS_35) {
		TS_35 = tS_35;
	}
	public String getTS_36() {
		return TS_36;
	}
	public void setTS_36(String tS_36) {
		TS_36 = tS_36;
	}
	public String getTS_37() {
		return TS_37;
	}
	public void setTS_37(String tS_37) {
		TS_37 = tS_37;
	}
	public String getTS_38() {
		return TS_38;
	}
	public void setTS_38(String tS_38) {
		TS_38 = tS_38;
	}
	public String getTS_39() {
		return TS_39;
	}
	public void setTS_39(String tS_39) {
		TS_39 = tS_39;
	}
	public String getTS_40() {
		return TS_40;
	}
	public void setTS_40(String tS_40) {
		TS_40 = tS_40;
	}
	public String getTS_41() {
		return TS_41;
	}
	public void setTS_41(String tS_41) {
		TS_41 = tS_41;
	}
	
}
