// package skeleton-fa20.proj0;

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;    
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r = Math.hypot(dx, dy);//返回所有参数的平方和的平方根
        return r;
    }

    public double calcForce(Body b){
        double G = 6.67e-11;//
        double r1=calcDistance(b);
        double F=G*this.mass*b.mass/(r1*r1);
        return F;
    }

    public double calcForceExertedByX(Body b){
        double Fx = this.calcForce(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
        return Fx;
    }

    public double calcForceExertedByY(Body b){
        double Fy = this.calcForce(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        double FxNet = 0;
        for (Body i:allBodys){
            if (!this.equals(i)){
                FxNet += this.calcForceExertedByX(i);
            }
        }
        return FxNet;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        double FyNet = 0;
        for (Body i:allBodys){
            if (!this.equals(i)){
                FyNet += this.calcForceExertedByY(i);
            }
        }
        return FyNet;
    }

    public void update(double dt,double Fx,double Fy){
        double aNetX = Fx/this.mass;
        double aNetY = Fy/this.mass;
        this.xxVel += aNetX * dt;
        this.yyVel += aNetY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}
