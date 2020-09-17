/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauss.seidel;

/**
 *
 * @author Salcedo
 */
public class gaussSeidelMethod {
    private double[][] a;
    private double[]b;
    private int n;
    private double[] x;
    private double es;
    private double lambda;
    private int imax;
    private String outputText;

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public int getImax() {
        return imax;
    }

    public void setImax(int imax) {
        this.imax = imax;
    }

  

    public gaussSeidelMethod() {
    }

    public double[][] getA() {
        return a;
    }

    public void setA(double[][] a) {
        this.a = a;
    }

    public double[] getB() {
        return b;
    }

    public void setB(double[] b) {
        this.b = b;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double getEs() {
        return es;
    }

    public void setEs(double es) {
        this.es = es;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }
    
    //Methods for solving x!
    
    public double[] solve(){
        String bufferString= "";
        double[] ea= new double[this.n+1];
        double dummy;
        double sum;
        int iter;
        boolean sentinel;
        double old;
        for(int i=1; i<=this.n; i++){
        dummy = this.a[i][i];
        
        for(int j=1; j<=this.n; j++){
            this.a[i][j]=this.a[i][j]/dummy;
        }
        this.b[i]=this.b[i]/dummy;
        }
        for(int i=1; i<=this.n; i++){
            sum=this.b[i];
        for(int j=1; j<=this.n; j++){
            if(i!=j){
            sum = sum-this.a[i][j]*this.x[j];
            }
        }
            this.x[i]=sum;
            this.x[i]=Math.round(this.x[i]*100000.0)/100000.0;
           bufferString+=" x["+i+"] = "+this.x[i];
        }
        
        
        iter=1;
        sentinel = true;
        
        while(sentinel){
           bufferString+="\n";
        for(int i=1; i<=this.n; i++){
           
            old=this.x[i];
            sum=this.b[i];
            for(int j=1; j<=this.n; j++){
            if(i!=j){
            sum = sum-this.a[i][j]*this.x[j];
            }
            }
            //this.x[i]=this.lambda*sum+(1-this.lambda)*old; only use this if you use relaxation
            this.x[i]=sum;
             //round-off to nearest 5 decimal places
            this.x[i]=Math.round(this.x[i]*100000.0)/100000.0;
            bufferString+=" x["+i+"] = "+this.x[i]+" ";
            
            if(sentinel&&this.x[i]!=0){
            ea[i] = Math.abs((this.x[i]-old)*100/this.x[i]);
            ea[i] = Math.round(ea[i]*100000.0)/100000.0;
           
            }
            
        }
        int passError=0;
        String ErrorValues="";
        for (int i=1; i<=this.n;  i++){
        if(ea[i]<=this.es){
            ErrorValues+="Error1:"+ea[i]+"\n";
        passError=passError+1;
        }
        }
        if(passError==this.n){
            bufferString+= "\n\n Final Error Values:";
            bufferString+=ErrorValues;
        sentinel=false;
        }    
        
        if(!sentinel||iter>=this.imax){
           bufferString+="Total number of iterations: "+iter+"\n";
           setOutputText(bufferString);
           break;
        }
        iter = iter+1;
        }
        
    return this.x;
    }
    
    
    
    
}
