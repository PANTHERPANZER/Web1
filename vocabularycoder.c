#include <stdio.h>
#include <stdlib.h>

int printhello()
{
	printf("+-------------------------------------------------------------------+\n");
	printf("|                                                                   |\n");
	printf("|                    Welcome to Enigma,boy                          |\n");
	printf("|    Be trust that everything will be better after spilt it out.    |\n");
	printf("|                                                                   |\n");
	printf("+-------------------------------------------------------------------+\n");
	printf("|                                                                   |\n");
	printf("|                      1.Code what I say                            |\n");
	printf("|                                                                   |\n");
	printf("|                     2.Decode what I said                          |\n");
	printf("|                                                                   |\n");
	printf("+-------------------------------------------------------------------+\n");
	printf("|          From PANZER_LEE HIT2012,all rights reserved              |\n");
	printf("+-------------------------------------------------------------------+\n");
	return 0;        
}

int main()
{
	FILE* fp;
	char a[600];
	int i,ops;
L1:system("cls");
	printhello();
	if(scanf("%d",&ops)!=1)
	{
		fflush(stdin);
		printf("Option ERROR!Press any key to reset!\n");
		getch();
		goto L1;
	}
	else
	{
		fflush(stdin);
		system("cls");
		printf("So,what is it?\n");
		if(ops==1)
		{
			gets(a);
			for(i=0;i<=599;i++)
			{
				if((a[i]>='A'&&a[i]<'Z')||(a[i]>='a'&&a[i]<'z'))
				{
					a[i]++;
				}
				else if(a[i]=='z')
				{
					a[i]='a';
				}
				else if(a[i]=='Z')
				{
					a[i]='A';
				}
			}
		}
		else if(ops==2)
		{
			gets(a);
			for(i=0;i<=599;i++)
			{
				if((a[i]>'A'&&a[i]<='Z')||(a[i]>'a'&&a[i]<='z'))
				{
					a[i]--;
				}
				else if(a[i]=='a')
				{
					a[i]='z';
				}
				else if(a[i]=='A')
				{
					a[i]='Z';
				}
			}
		}
	}
	fp=fopen("C:\\Users\\dell\\Desktop\\mycoder.txt","w");
	fprintf(fp,"%s",a);
	fclose(fp);
	printf("Result already put into the new TXT on your desktop,wish you a good mood soon!\n");
	return 0;
}