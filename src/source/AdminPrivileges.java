package source;
interface AdminPrivileges {
    void renameFile(String oldName, String newName);
    void updateUserPrivilege(String username, String newPrivilege);
}
