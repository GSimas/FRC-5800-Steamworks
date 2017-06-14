package org.usfirst.frc.team5800.robot.superclasses;

public class PID {
	// img de demonstra��o : https://upload.wikimedia.org/wikipedia/commons/4/43/PID_en.svg
	
	double error; // diff entre o valor objetivo e valor medido
	double sample; //ponto de dado
	double lastSample; // ultimo ponto de dado
	double kP, kI, kD; // ganhos dos controladores
	double P, I, D; //valores de sa�da dos compensadores
	double pid; //resultado final
	  
	double setPoint; //Valor objetivo
	long lastProcess; //instante da �ltima leitura, em milis.
	  
	 // Seta ganhos do PID
	public static void setGains(double _kP, double _kI, double _kD) {
		kP = _kP;
	    kI = _kI;
	    kD = _kD;
	  }
	  
	  public static void addNewSample(double _sample){
	    sample = _sample; // adiciona um novo ponto de dado 
	  }
	  
	  public static void setSetPoint(double _setPoint){
	    setPoint = _setPoint; //seta valor a ser atingido
	  }
	  
	  public static double process() {
	    // Implementa��o PID
	    error = setPoint - sample; // Calcula erro
	    double deltaTime = (System.currentTimeMillis(); - lastProcess) / 1000.0; // calcula varia��o de tempo entre dois pontos de dados
	    lastProcess = System.currentTimeMillis();
	    
	    //P --> proporcional ao erro, apenas um ganho em cima do erro
	    P = error * kP;
	    
	    //I --> inversamente proporcional � quanto falta para atingir o valor desejado, "desacelera" quando pr�ximo do setpoint, para evitar overshoot
	    I = I + (error * kI) * deltaTime;
	    
	    //D --> Proporcional � qu�o r�pido ocorre a mudan�a 
	    D = (lastSample - sample) * kD / deltaTime;
	    lastSample = sample;
	    
	    // Soma tudo
	    pid = P + I + D;
	    
	    return pid;
	  }

}
