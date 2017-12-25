package generators;

public class CreateOTP {
	static String otpString;
	static private CreateOTP otp;
	// 6 Character OTP will be created.
	public CreateOTP(){
		StringBuilder otpBuilder = new StringBuilder();
		RandomCharacterGenerator rcg = new RandomCharacterGenerator();
		for(int i=0; i<6;i++){
			otpBuilder.append(rcg.generateChar());
		}
		otpString = otpBuilder.toString();
	}
	
	public static String getOTP(){
		CreateOTP.getInstance();
		return CreateOTP.otpString;
	}
	public static CreateOTP getInstance(){
		otp = new CreateOTP();
		/*if(otp == null){
			otp = new CreateOTP();
		}*/
		return otp;
	}
}
