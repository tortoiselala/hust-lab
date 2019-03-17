/**
 * 
 */
package salary.managment.system.base;

import salary.managment.system.Exception.NumIllegalException;

/**
 * @author Tortoise
 *
 */
public class DecreaseSalary extends BasicInformationOfEmployee {

	// sickDeduction 病事扣款
	private double sickFee;
	// childCareFee 托儿费
	private double childCareFee;
	// rent 房租
	private double rentFee;
	// waterElectricityFee 水电
	private double waterElectricityFee;
	// otherFee 其他扣款
	private double otherFee;

	/**
	 * 
	 * @param id
	 * @param name
	 * @param sex
	 * @param age
	 * @param baseSalary
	 * @param category
	 * @throws Exception
	 * @throws NumIllegalException
	 */
	public DecreaseSalary(String id, String name, short sex, short age, double baseSalary, String category)
			throws Exception, NumIllegalException {
		super(id, name, sex, age, baseSalary, category);
		setSickFee(0);
		setChildCareFee(0);
		setRentFee(0);
		setWaterElectricityFee(0);
		setOtherFee(0);

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param sex
	 * @param age
	 * @param baseSalary
	 * @param category
	 * @param sickFee
	 * @param childCareFee
	 * @param rentFee
	 * @param waterElectricityFee
	 * @param otherFee
	 * @throws Exception
	 * @throws NumIllegalException
	 */
	public DecreaseSalary(String id, String name, short sex, short age, double baseSalary, String category,
			double sickFee, double childCareFee, double rentFee, double waterElectricityFee, double otherFee)
			throws Exception, NumIllegalException {
		super(id, name, sex, age, baseSalary, category);
		setSickFee(sickFee);
		setChildCareFee(childCareFee);
		setRentFee(rentFee);
		setWaterElectricityFee(waterElectricityFee);
		setOtherFee(otherFee);
	}

	/**
	 * 
	 * @return
	 */
	public double getSumDecrease() {
		return getSickFee() + getChildCareFee() + getRentFee() + getWaterElectricityFee() + getOtherFee();
	}

	/**
	 * 
	 * @return
	 */
	public double getSalary() {
		return getBaseSalary() - getSumDecrease();
	}

	/**
	 * @return the sickFee
	 */
	public double getSickFee() {
		return sickFee;
	}

	/**
	 * @param sickFee the sickFee to set
	 * @throws Exception
	 */
	public void setSickFee(double sickFee) throws NumIllegalException {
		if (sickFee < 0) {
			throw new NumIllegalException();
		}
		this.sickFee = sickFee;
	}

	/**
	 * @return the childCareFee
	 */
	public double getChildCareFee() {
		return childCareFee;
	}

	/**
	 * @param childCareFee the childCareFee to set
	 * @throws Exception
	 */
	public void setChildCareFee(double childCareFee) throws NumIllegalException {
		if (childCareFee < 0) {
			throw new NumIllegalException();
		}
		this.childCareFee = childCareFee;
	}

	/**
	 * @return the rentFee
	 */
	public double getRentFee() {
		return rentFee;
	}

	/**
	 * @param rentFee the rentFee to set
	 * @throws Exception
	 */
	public void setRentFee(double rentFee) throws NumIllegalException {
		if (rentFee < 0) {
			throw new NumIllegalException();
		}
		this.rentFee = rentFee;
	}

	/**
	 * @return the waterElectricityFee
	 */
	public double getWaterElectricityFee() {
		return waterElectricityFee;
	}

	/**
	 * @param waterElectricityFee the waterElectricityFee to set
	 * @throws Exception
	 */
	public void setWaterElectricityFee(double waterElectricityFee) throws NumIllegalException {
		if (waterElectricityFee < 0) {
			throw new NumIllegalException();
		}
		this.waterElectricityFee = waterElectricityFee;
	}

	/**
	 * @return the otherFee
	 */
	public double getOtherFee() {
		return otherFee;
	}

	/**
	 * @param otherFee the otherFee to set
	 * @throws Exception
	 */
	public void setOtherFee(double otherFee) throws NumIllegalException {
		if (otherFee < 0) {
			throw new NumIllegalException();
		}
		this.otherFee = otherFee;
	}

}
