package massa.util;

public class Info {
	private String inf1;
	private String inf2;

	public Info(String inf1, String inf2) {
		setInf1(inf1);
		setInf2(inf2);
	}

	@Override
	public String toString() {
		return "[" + inf1 + " , " + inf2 + "]";
	}

	public String getInf1() {
		return inf1;
	}

	public void setInf1(String inf1) {
		this.inf1 = inf1;
	}

	public String getInf2() {
		return inf2;
	}

	public void setInf2(String inf2) {
		this.inf2 = inf2;
	}

}// end class Info