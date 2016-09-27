#include <bits/stdc++.h>
float distanceMat[10][10] = {9999};


struct cordinate
{
	float x;
	float y;
	// float z;
	cordinate(float x,float y)
	{
		this->x = x;
		this->y = y;
		// this->x = z;
	}
	cordinate()
	{
		this->x = 999999;
		this->y = 999999;
	}
};

struct cluster
{
	float x[10];
	float y[10];
	int i;
	cluster()
	{
		i = 0;
	}
};

struct cluster addToCluster(cordinate b,struct cluster a)
{
	// printf("Adding : %f , %f to a[%d]\n",b.x,b.y,a.i);
	a.x[a.i] = b.x;
	a.y[a.i] = b.y;
	a.i += 1;
	return a;
}

float distCalc(struct cordinate a,struct cordinate b)
{
	return (pow(((pow((a.x-b.x),2))+pow((a.y-b.y),2)),0.5));
	//+pow((a->Z-b->Z),2));
}

struct cluster mergeCluster(struct cluster a,struct cluster b)
{
	for(int i=0 ; i<b.i ; i++)
	{
		b.x[i] = a.x[a.i];
		b.y[i] = a.y[a.i];
		a.i += 1;
	}
	return a;
}

void updateDistanceMatrix(int points,struct cluster a[10])
{
	for(int i=0 ; i<points ; i++)
	{
		for(int j=0 ; j<points ; j++)
		{
			for(int k=0 ; k<a[i].i ; k++)
			{
				for(int l=0 ; l<a[j].i ; l++)
				{
					float cur = distCalc(cordinate(a[i].x[k],a[i].y[k]),cordinate(a[j].x[l],a[j].y[l]));
					// printf("%d %d %d %d %f %f = a.x: %f a.y: %f a.x: %f a.y: %f",i,j,k,l,cur,distanceMat[i][j],a[i].x[k],a[i].y[k],a[j].x[l],a[j].y[l]);
					if(distanceMat[i][j] > cur)
						distanceMat[i][j] = cur;
				}
			}
			// printf("%f\t",distanceMat[i][j]);

		}
		// printf("\n");
	}
}

void printCluster(struct cluster a)
{
	for(int i=0 ; i<a.i ; i++)
	{
		printf("(%f,%f)\n",a.x[i],a.y[i]);
	}
	printf("\n");
	return;
}

void distanceMatrix(int points)
{
	for(int i=0 ; i<points ; i++)
	{
		for(int j=0 ; j<points ; j++)
			distanceMat[i][j] = 99999;
	}
}

void printDistanceMatrix(int points)
{
	for(int i=0 ; i<points ; i++)
	{
		for(int j=0 ; j<points ; j++)
			printf("%f\t",distanceMat[i][j]);
		printf("\n");
	}
}

struct closestCluster
{
	struct cluster a;
	struct cluster b;
	closestCluster(struct cluster a,struct cluster b)
	{
		this->a = a;
		this->b = b;
	}
};

struct closestCluster findClosestCluster(struct cluster a[10], int points)
{
	float mindis = 9999999;
	int mini=0,minj=0;
	for(int i=0 ; i<points ; i++)
	{
		for(int j=0 ; j<points ; j++)
			if(distanceMat[i][j] < mindis && distanceMat[i][j] != 0 && i!=j)
			{
				mindis = distanceMat[i][j];
				mini = i;
				minj = j;
			}
	}
	return closestCluster(a[mini],a[minj]);
}

void printClosestCluster(struct closestCluster a)
{
	int i;
	for(i=0 ; i<a.a.i ; i++)
	{
		printf("(%f,%f),",a.a.x[i],a.a.y[i]);
	}
	for(i=0 ; i<a.b.i ; i++)
	{
		printf("(%f,%f),",a.b.x[i],a.b.y[i]);
	}
	return;
}

int main()
{
	struct cordinate a[10];
	struct cluster b[10];
	int points = 6;
	a[0] = cordinate(1,1);
	a[1] = cordinate(1.5,1.5);
	a[2] = cordinate(5,5);
	a[3] = cordinate(3,4);
	a[4] = cordinate(4,4);
	a[5] = cordinate(3,3.5);
	distanceMatrix(points);
	for(int i=0 ; i<points ; i++)
		b[i] = addToCluster(a[i],b[i]);
	for(int i=0 ; i<points ; i++)
		printCluster(b[i]);
	updateDistanceMatrix(points,b);
	int clusterNumber = points;
	while(clusterNumber != 1)
	{
		distanceMatrix(points);
		updateDistanceMatrix(clusterNumber,b);
		struct closestCluster c = findClosestCluster(b,clusterNumber);
		printClosestCluster(c);
		printf("\n");
		// findClosestCluster(struct cluster a[10],int clusterNumber);
		clusterNumber--;
		printDistanceMatrix(points);
	}
}