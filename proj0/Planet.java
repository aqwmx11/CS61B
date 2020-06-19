public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos       = xP;
        yyPos       = yP;
        xxVel       = xV;
        yyVel       = yV;
        mass        = m;
        imgFileName = img;
    }

    public Planet(Planet P) {
        xxPos       = P.xxPos;
        yyPos       = P.yyPos;
        xxVel       = P.xxVel;
        yyVel       = P.yyVel;
        mass        = P.mass;
        imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet P) {
        double dx = P.xxPos - xxPos;
        double dy = P.yyPos - yyPos;
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance;
    }

    public double calcForceExertedBy(Planet P) {
        double distance = this.calcDistance(P);
        double force    = G * P.mass * this.mass / distance / distance;

        return force;
    }

    public double calcForceExertedByX(Planet P) {
        double dx       = P.xxPos - this.xxPos;
        double force    = this.calcForceExertedBy(P);
        double distance = this.calcDistance(P);
        double forceX   = force * dx / distance;

        return forceX;
    }

    public double calcForceExertedByY(Planet P) {
        double dy       = P.yyPos - this.yyPos;
        double force    = this.calcForceExertedBy(P);
        double distance = this.calcDistance(P);
        double forceY   = force * dy / distance;

        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for(Planet P : allPlanets) {
            if(!this.equals(P)) {
                netForceX += this.calcForceExertedByX(P);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for(Planet P : allPlanets) {
            if(!this.equals(P)) {
                netForceY += this.calcForceExertedByY(P);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        String real_imgFileName = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, real_imgFileName);
    }
}
