public class NBody {
    public static double readRadius(String s) {
        In in = new In(s);
        int planetNum = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String s) {
        In in = new In(s);
        int planetNum = in.readInt();
        double radius = in.readDouble();
        Planet[] allPlanets = new Planet[planetNum];
        for(int index = 0; index < planetNum; index += 1) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allPlanets[index] = new Planet(xP, yP, xV, yV, m, img);
        }
        return allPlanets;
    }

    public static void main(String[] Args) {
        //read the inputs
        double T = Double.parseDouble(Args[0]);
        double dt = Double.parseDouble(Args[1]);
        String filename = Args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        int planetNum = allPlanets.length;

        //draw the background
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        //draw all planets
        for(Planet P : allPlanets) {
            P.draw();
        }

        StdDraw.show();

        //create animations
        StdDraw.enableDoubleBuffering();
        Double time = 0.0;
        while(time < T) {
            double[] xForces = new double[planetNum];
            double[] yForces = new double[planetNum];

            for(int i = 0; i < planetNum; i += 1) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }

            for(int i = 0; i < planetNum; i += 1) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            //draw the background
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");

            //draw all planets
            for(Planet P : allPlanets) {
                P.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}
