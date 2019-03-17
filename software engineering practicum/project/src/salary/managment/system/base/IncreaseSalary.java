/**
 * 
 */
package salary.managment.system.base;

import salary.managment.system.Exception.NumIllegalException;

/**
 * @author Tortoise
 *
 */
public class IncreaseSalary extends BasicInformationOfEmployee {

	private double bonus; // 员工本月奖金
	private double allowance; // 员工本月补贴
	private double overtimePay;// 加班工资
	private double other;// 其他补贴

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
	public IncreaseSalary(String id, String name, short sex, short age, double baseSalary, String category)
			throws Exception, NumIllegalException {
		super(id, name, sex, age, baseSalary, category);
		setBonus(0);
		setAllowance(0);
		setOvertimePay(0);
		setOther(0);
	}

	/**
	 * 
	 * @param id          员工唯一身份识别信息，身份证号
	 * @param name        员工姓名
	 * @param sex         员工性别
	 * @param age         员工年龄
	 * @param baseSalary  员工基础月薪
	 * @param category    员工类别
	 * @param bonus       本月奖金
	 * @param allowance   本月补贴
	 * @param overtimePay 本月加班费用
	 * @param other       本月其他奖金
	 * @throws Exception
	 */
	public IncreaseSalary(String id, String name, short sex, short age, double baseSalary, String category,
			double bonus, double allowance, double overtimePay, double other) throws Exception, NumIllegalException {
		super(id, name, sex, age, baseSalary, category);
		setBonus(bonus);
		setAllowance(allowance);
		setOvertimePay(overtimePay);
		setOther(other);
	}

	/**
	 * 
	 * @return 所有奖金的和
	 */
	public double getSumIncrease() {
		return getBonus() + getAllowance() + getOvertimePay() + getOther();
	}

	public double getSalary() {
		return getBaseSalary() + getSumIncrease();
	}

	/**
	 * @return the bonus
	 */
	public double getBonus() {
		return bonus;
	}

	/**
	 * @param bonus the bonus to set
	 * @throws Exception
	 */
	public void setBonus(double bonus) throws NumIllegalException {
		if (bonus < 0) {
			throw new NumIllegalException();
		}
		this.bonus = bonus;
	}

	/**
	 * @return the allowance
	 */
	public double getAllowance() {
		return allowance;
	}

	/**
	 * @param allowance the allowance to set
	 * @throws Exception
	 */
	public void setAllowance(double allowance) throws NumIllegalException {
		if (allowance < 0) {
			throw new NumIllegalException();
		}
		this.allowance = allowance;
	}

	/**
	 * @return the overtimePay
	 */
	public double getOvertimePay() {
		return overtimePay;
	}

	/**
	 * @param overtimePay the overtimePay to set
	 * @throws Exception
	 */
	public void setOvertimePay(double overtimePay) throws NumIllegalException {
		if (overtimePay < 0) {
			throw new NumIllegalException();
		}
		this.overtimePay = overtimePay;
	}

	/**
	 * @return the other
	 */
	public double getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 * @throws Exception
	 */
	public void setOther(double other) throws NumIllegalException {
		if (other < 0) {
			throw new NumIllegalException();
		}
		this.other = other;
	}

}
