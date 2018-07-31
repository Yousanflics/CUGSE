struct TreeNode{
	int val;
	TreeNode *right, *left;
}
class solution{
public:
	int Max = -1000000000000000;
	int NodeTree * l;
	TreeNode * MaxNode(NodeTree * root){
	if(root == NULL){
	return NULL;
}
	if(root->val > Max){
	Max = root->val;
	l = root;
}
	MaxNode(root->right);
	MaxNode(root->left);
	return l;
}
};
