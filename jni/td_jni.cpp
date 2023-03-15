#include "td_jni.h"
#include "td/telegram/Client.h"

using ClientManager = td::ClientManager;

void Java_me_omico_telegram_tdlib_Jni_init(JNIEnv *, jclass) {
  printf("Hello, World!");
}

ClientManager *get_manager() { return ClientManager::get_manager_singleton(); }

jint Java_me_omico_telegram_tdlib_Jni_createNativeClient(JNIEnv *, jclass) {
  return static_cast<jint>(get_manager()->create_client_id());
}
