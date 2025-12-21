class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        int len = meetings.length ;

        Arrays.sort(meetings, (a,b) -> Integer.compare(a[2], b[2])) ;

        boolean[] knows = new boolean[n] ;
        knows[0] = knows[firstPerson] = true ;

        int[] parent = new int[n] ;
        for(int i = 0 ; i < n ; i ++)
        {
            parent[i] = i ;
        }

        int i = 0 ;

        int j = 0 ;

        while(i < len && j < len)
        {
            while(j < len && meetings[i][2] == meetings[j][2])
            {
                j ++ ;
            }

            for(int idx = i ; idx < j ; idx ++)
            {
                int[] meeting = meetings[idx] ;

                int x = meeting[0] ;
                int y = meeting[1] ;
                
                union(x,y,parent,knows) ;
            }

            for(int idx = i ; idx < j ; idx ++)
            {
                int[] meeting = meetings[idx] ;

                int x = meeting[0] ;
                int y = meeting[1] ;

                int a = findParent(x, parent) ;
                int b = findParent(y, parent) ;

                if(knows[a]) knows[x] = true ;
                if(knows[b]) knows[y] = true ;
            }

            for(int idx = i ; idx < j ; idx ++)
            {
                int[] meeting = meetings[idx] ;

                int x = meeting[0] ;
                int y = meeting[1] ;

                parent[x] = x ;
                parent[y] = y ;
            }

            i = j ;
        }

        List<Integer> list = new ArrayList<>() ;
        for(int idx = 0 ; idx < n ; idx ++)
        {
            if(knows[idx]) list.add(idx) ;
        }

        return list ;
    }

    void union(int x, int y, int[] parent, boolean[] knows)
    {
        int a = findParent(x, parent) ;
        int b = findParent(y, parent) ;

        if(knows[a])
        {
            parent[b] = a ;
        }
        else parent[a] = b ;

        return ;
    }

    int findParent(int x, int[] parent)
    {
        if(x == parent[x])
        {
            return x ;
        }

        return parent[x] = findParent(parent[x], parent) ;
    }
}