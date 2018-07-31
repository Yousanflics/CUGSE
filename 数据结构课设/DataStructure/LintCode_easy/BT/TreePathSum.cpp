/**
 * Definition of TreeNode:
 * class TreeNode {
 * public:
 *     int val;
 *     TreeNode *left, *right;
 *     TreeNode(int val) {
 *         this->val = val;
 *         this->left = this->right = NULL;
 *     }
 * }
 */


class Solution {
public:
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    vector<vector<int>> binaryTreePathSum(TreeNode * root, int target) {
        // write your code here
        vector<int> path;
        vector<vector<int> > patharr;

        if(root == NULL){
            return patharr;
        }
        int cursum = 0;
        if(root != NULL){
            return binaryTreePath(root,target,path, patharr,cursum);
        }
    }

    vector<vector<int>> binaryTreePath(TreeNode *root, int target,vector<int> &path,vector<vector<int>> &patharr,int sum){

        if(root == NULL){
            return patharr;
        }

        if(root!=NULL){
            sum += root->val;
            path.push_back(root->val);

            if(root->left == NULL && root->right == NULL){
                //cout<<sum<<endl;
                if(sum == target){
                    patharr.push_back(path);
                    /*vector<int>::iterator iter = path.begin();
                    for(; iter!=path.end(); iter++){
                        cout<<*iter<<" ";
                    }*/

                    //return patharr;
                }
            }
            if(root->left != NULL){
                 binaryTreePath(root->left,target,path,patharr,sum);
            }
            if(root->right != NULL){
                binaryTreePath(root->right,target,path,patharr,sum);
            }
            sum -= *path.end();
            path.pop_back();
            return patharr;
        }

    }
    
};
