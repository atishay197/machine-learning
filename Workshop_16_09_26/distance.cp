#include <bits/stdc++.h>

struct cordinate
{
	float x;
	float y;
	// float z;
	cordinate(float x,float y)
	{
		this.x = x;
		this.y = y;
		// this.x = z;
	}
};

float distCalc(struct cordinate a,struct cordinate b)
{
	return (sqrt(pow(a.X-b.X),2)+pow((a.Y-b.Y),2));//+pow((a.Z-b.Z),2));
}


int main()
{
	struct cordinate a[10];
	float distanceMat[10][10] = {0};
	int points = 5;
	a[0] = cordinate(1,1);
	a[1] = cordinate(15,15);
	a[2] = cordinate(5,4);
	a[3] = cordinate(4,4);
	a[4] = cordinate(3,3);

	for(i=0 ; i<points ; i++)
	{
		for(j=0 ; j<points ; j++)
		{
			printf("%f\t",distCalc(a[i],a[j]));
		}
		printf("\n");
	}
}