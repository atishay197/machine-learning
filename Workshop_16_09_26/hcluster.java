/* 
PROGRAM TO SHOWCASE HIERARCHICAL CLUSTERING PROBLEM IMPLEMENTED IN JAVA
=======================================================================
@author Karanjit Tiwana
@author Vibhor Varshney
@version java 1.5 +
=======================================================================

Note: Make sure that you change the path address of your dataset file in the main method.
@structure
<classname> clustering

			<method> getdata()		:	The method takes the object attributes from the dataset based on the delimiter \t
										@param:path			@type:string	@description:directory path of the dataset file
										@returns void
								
			<method>cluster_matrix():	The method forms the matrix to identify the closest path based on Euclidian Mean square method and calls the clust() method.
										@param:data[][]		@type:double	@description:the matrix of object attributes retreived from dataset.
										@param:r			@type:int		@description:no. of rows or the no. of objects.
										@returns void
										
			<method> clust()		:	The method takes the matrix,finds the closest pair of object based on their attributes and clusters them.This method is recusive 
										in nature wherein it updates the matrix in each run.
										@param matrix[][]	@type:double	@description:the initial matrix comprising of distances between all objects.
										@param r			@type:int		@description: no. of objects/no of rows in dataset.
										@param k			@type:int		@description:no of recursive iterations to be performed.
										@param str			@type:StringBuilder object	@description:Output string appended with clusters.
										@param C[]			@type:String	@description:temporary string for used for computing clustered output.
										@returns void
										
*/

import java.io.*;
import java.lang.*;

class clustering
{
	void getdata(String path) throws IOException //This method extract attributes(columns) of the objects from the data file.
	{
		BufferedReader 	in = new BufferedReader(new FileReader(path));

		
		int c=0,r=0,tempc=0,tempr=0;
		String str="";
		
		//finding no of rows and columns
		try {

		while ((str = in.readLine()) != null)
			 {
				String[] ar=str.split(","); //change the delimiter here
				tempc=ar.length;
				if(c<tempc)
				{
					c=tempc;
				}
				r++;
			}		
			in.close();
       		    } 
		    catch (IOException e) 
		    {
            		System.out.println("File Read Error");
        	    }
			
		//creating the array
		double arr[][]=new double[r][c];


	BufferedReader 	in2 = new BufferedReader(new FileReader(path));

		//inserting values in array
		try {
            		 while ((str = in2.readLine()) != null)
			{
				String[] ar=str.split(",");
				tempc=ar.length;
				for(int j=0;j<tempc;j++)
				{
					arr[tempr][j]=Double.parseDouble(ar[j]);
							
				}
				tempr++;
				
            } 
            		in.close();
       		} 
		    catch (IOException e) 
		    {
            		System.out.println("File Read Error2");
        	    }
		    
                    //Display array
		    for(int i=0;i<r;i++)
		    {
			for(int j=0;j<c;j++)
			{
				System.out.print(arr[i][j] + "\t");
			}
			System.out.print("\n");
		    }	
        	

				cluster_matrix(arr,r);

	}
	
	void cluster_matrix(double data[][],int r)//This method forms the Matrix to identify the closest pair based on Euclidian Mean square method.
	{
		
		int i,j; 
		double matrix[][]=new double[r][r]; 
		
		for(i=0;i<r;i++)
		{
			for(j=0;j<r;j++)
			{
				matrix[i][j]=Math.sqrt(Math.pow((data[i][0]-data[j][0]),2) + Math.pow((data[i][1]-data[j][1]),2));
			}
		}
		
		System.out.println("Displaying Initial Matrix\n");
		 for(i=0;i<r;i++)
		{
			for(j=0;j<r;j++)
			{
				System.out.printf("%8.2f",matrix[i][j]);
			}
			System.out.println();
		}
		
		StringBuilder str = new StringBuilder();
		int k=r; //Intitalizing k for r recursive iteration of method clust.
		String C[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; // temporary string used for clustered output using parenthesis
		clust(matrix,r,k,str,C); 
		
		System.out.println("\nClustered Objects :\n\n"+str); //String diplays clustered output after all the Recursive iterations are over.
	
	}
	
	void clust(double matrix[][],int r,int k,StringBuilder str,String C[])//This method finds the closest pair of object the form clusters accordingly.
	{
		k--;
		if(k>0)
		{
			int i,j,p=0,q=0;
		double temp=1000000000;
		
		for(i=0;i<r;i++)
		{
			for(j=0;j<r;j++)
			{
				
				if((i<j) && (matrix[i][j]<temp)) //Condition finds the smallest value from the matrix(upper triangular matrix).
				{
					temp=matrix[i][j];
					p=i;
					q=j;
				} // p and q gives the index value for closest pair in the Matrix.
			}
		}
		
		C[p]="("+C[p]+C[q]+")"; //temporary matrix used for displaying clustered output(with parenthesis)
		str.append(C[p]); //Appending the clusters one by one to str.
		str.append(',');	
		for(i=0;i<r;i++) //Loop to merge two closest pair.Based on the minimum value(out of the 2) matrix is updated.
		{
			if(matrix[p][i]>matrix[q][i])
			{
				matrix[p][i]=matrix[q][i];
			}
			if(matrix[i][p]>matrix[i][q])
			{
				matrix[i][p]=matrix[i][q];
			}
		}
		
		/*for(i=0;i<r;i++) //assigning the largest value of Double datatype to one the object distance which is to be merged in a matrix.
		{
			matrix[q][i]=Double.MAX_VALUE;
			matrix[i][q]=Double.MAX_VALUE;
		}*/
		for(i=0;i<r;i++) //to make the display of matrix easier we  have taken 10 since the square root of a number will not be a large value.
		{
			matrix[q][i]=10.0;
			matrix[i][q]=10.0;
		}
		System.out.println("===============================================");
		for(i=0;i<r;i++)
		{
			for(j=0;j<r;j++)
			{
				System.out.printf("%8.2f",matrix[i][j]);
			}
			System.out.println();
		}
		
		clust(matrix,r,k,str,C);

		}
	}
}

class hcluster
{
	public static void main(String [] args) throws IOException
	{
		clustering c= new clustering();
		c.getdata("testing in rows.csv"); //path of your dataset file.
	}
}